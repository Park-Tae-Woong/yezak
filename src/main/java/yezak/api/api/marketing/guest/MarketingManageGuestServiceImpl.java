package yezak.api.api.marketing.guest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.marketing.admin.dto.MarketingManageAdminRegistPatientRequest;
import yezak.api.api.marketing.guest.dto.MarketingManageGuestRegistRequest;
import yezak.api.api.marketing.guest.dto.MarketingManageGuestRegistResponse;
import yezak.api.global.common.ResultResponse;
import yezak.api.api.marketing.admin.MarketingManageAdminMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static yezak.api.config.MyIdConfig.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class MarketingManageGuestServiceImpl implements MarketingManageGuestService {

    private final MarketingManageGuestMapper mapper;
    private final MarketingManageAdminMapper adminMapper;

    private final Integer marketingGuestId = 17;
    private final Integer marketingAdminId = 15;
    @Override
    public ResultResponse<?> getList() {
        if(myDepth3Id().contains(marketingGuestId) || myDepth3Id().contains(marketingAdminId)) {
            return ResultResponse.builder()
                    .data(mapper.getList(myHospitalId()))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Transactional
    @Override
    public ResultResponse<?> registDb(String title, Long accessRootId, Long amount, String executionDate, MultipartFile file) {
        if(myDepth3Id().contains(marketingGuestId) || myDepth3Id().contains(marketingAdminId)) {
            MarketingManageGuestRegistRequest request = new MarketingManageGuestRegistRequest();

            Set set = new HashSet<>();
            int result = 0;
            request.setHospitalId(myHospitalId());
            request.setUserId(myUserId());
            request.setTitle(title);
            request.setAccessRootId(accessRootId);
            request.setAmount(amount);
            request.setExecutionDate(LocalDate.parse(executionDate, DateTimeFormatter.ISO_DATE));
            mapper.registDb(request);
            try {
                if(file != null) {
                    //xlsx : 이름 / 전화번호 / 상담가능시간
//                String fileName = file.getOriginalFilename();
//                String path = "C:\\workspace\\"+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))+"\\";//local dir
//
//                if(!new File(path).exists()) new File(path).mkdir();
//
//                File dest = new File(path+file.getOriginalFilename());
//                file.transferTo(dest);
//
//                mapper.registFile(MarketingManageGuestFileRequest.builder()
//                        .patientImportId(request.getId())
//                        .fileName(fileName)
//                        .filePath(path)
//                        .fileExtention(fileName.substring(fileName.lastIndexOf(".") + 1))
//                        .fileSize(file.getSize())
//                        .build());

//                FileInputStream fileInputStream = new FileInputStream(dest);
//                Workbook workbook = new XSSFWorkbook(fileInputStream);
                    Workbook workbook = new XSSFWorkbook(file.getInputStream());
                    Sheet sheet = workbook.getSheetAt(0); // 업로드할 Excel 파일의 첫 번째 시트 선택

                    int startRow = 7; // 데이터 수집을 시작할 행 인덱스 (두 번째 행부터 시작)
                    int endRow = sheet.getLastRowNum(); // 마지막 행 번호

                    for (int rowNum = startRow; rowNum <= endRow; rowNum++) {
                        Map<String, Object> checkParamMap = new HashMap<>();
                        Row row = sheet.getRow(rowNum);

                        // 셀 값 가져오기
                        Cell nameCell = row.getCell(1); // B열 (0부터 시작)
                        Cell phoneCell = row.getCell(2); // C열 (0부터 시작)
                        Cell timeCell = row.getCell(3); // D열 (0부터 시작)

                        // 셀 값 확인
                        String name = "";
                        String phone = "";
                        String time = "";

                        if (nameCell != null && nameCell.getCellType() == CellType.STRING) {
                            name = nameCell.getStringCellValue();
                        }

                        if (phoneCell != null && phoneCell.getCellType() == CellType.STRING) {
                            phone = phoneCell.getStringCellValue().replaceAll("-","");
                        }

                        if (timeCell != null && timeCell.getCellType() == CellType.STRING) {
                            time = timeCell.getStringCellValue();
                        }

                        if((name+phone).length() > 0) set.add(name + phone);

                        // 빈 값을 제외하고 출력
                        if (!name.isEmpty() || !phone.isEmpty() || !time.isEmpty()) {
                            checkParamMap.put("name", name);
                            checkParamMap.put("phoneNumber", phone);
                            checkParamMap.put("hospitalId", myHospitalId());
                            Integer checkCnt = adminMapper.checkDbData(checkParamMap);
                            Map<String, Object> paramMap = new HashMap<>();
                            paramMap.put("name", name);
                            paramMap.put("phoneNumber", phone);
                            paramMap.put("hospitalId", myHospitalId());
                            if(checkCnt <= 0) {
                                paramMap.put("accessRootId", request.getAccessRootId());
                                mapper.registPatiantsData(paramMap);
                            }
                            if(mapper.checkPatientCounselingInfo(paramMap) <= 0) {
                                adminMapper.registPatientCounselingInfos(MarketingManageAdminRegistPatientRequest.builder()
                                        .name(name)
                                        .phoneNumber(phone)
                                        .availableTime(time)
                                        //                                .accessRoot(request.getAccessRootId())
                                        .build());
                            }
                            result++;
                        }
                    }

                    workbook.close();
//                fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            MarketingManageGuestRegistResponse marketingManageGuestRegistResponse =  MarketingManageGuestRegistResponse.builder()
                    .resultCount(result)
                    .overlapCount(result - set.size())
                    .build();

            return ResultResponse.builder()
                    .data(marketingManageGuestRegistResponse)
                    .result(true)
                    .resultCode("200")
                    .resultMessage("등록되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }
}
