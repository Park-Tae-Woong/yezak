package yezak.api.api.marketing.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.marketing.admin.dto.*;
import yezak.api.global.common.ResultResponse;
import yezak.api.api.marketing.guest.MarketingManageGuestMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yezak.api.config.MyIdConfig.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class MarketingManageAdminServiceImpl implements MarketingManageAdminService {


    private final MarketingManageAdminMapper mapper;
    private final MarketingManageGuestMapper guestMapper;
    private final Integer marketingAdminId = 15;
    private final Integer marketingUserId = 16;



    @Override
    public ResultResponse<?> getUserList() {
        if(myDepth3Id().contains(marketingAdminId) || myDepth3Id().contains(marketingUserId)) {
            return ResultResponse.builder()
                    .data(mapper.getUserList(myHospitalId()))
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

    @Override
    public ResultResponse<?> getCounselingStatusesList() {
        if(myDepth3Id().contains(marketingAdminId) || myDepth3Id().contains(marketingUserId)) {
            return ResultResponse.builder()
                    .data(mapper.getCounselingStatusesList())
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

    @Override
    public ResultResponse<?> deleteData(String idArr) {
        if (myDepth3Id().contains(marketingAdminId)) {
            String[] idArrs = idArr.replaceAll("\\s+", "").split(",");
            for (String id : idArrs) {
                if (!id.isEmpty()) {
                    mapper.deleteData(id);

                }
            }
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("삭제되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> setManualUserPatient(MarketingManageAdminSetManualUserPatientRequest request) {
        if(myDepth3Id().contains(marketingAdminId)) {
            Long chargeId = request.getChargeId();
            List<MarketingManageAdminSetManualUserPatientRawInfo> list = request.getRawInfoList();
            for (MarketingManageAdminSetManualUserPatientRawInfo marketingManageAdminSetManualUserPatientRawInfo : list) {
                Map paramMap = new HashMap();
                if (marketingManageAdminSetManualUserPatientRawInfo.getChargeId() == null) {
                    paramMap.put("userId", chargeId);
                    paramMap.put("patientId", marketingManageAdminSetManualUserPatientRawInfo.getId());
                    mapper.insertUserPatientManual(paramMap);

                } else {
                    paramMap.put("userId", chargeId);
                    paramMap.put("patientId", marketingManageAdminSetManualUserPatientRawInfo.getId());
                    paramMap.put("chargeId", marketingManageAdminSetManualUserPatientRawInfo.getChargeId());
                    mapper.updateUserPatient(paramMap);
                    return ResultResponse.builder()
                            .result(true)
                            .resultCode("200")
                            .resultMessage("수정되었습니다.")
                            .build();
                }
            }
            return ResultResponse.builder()
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
    @Override
    public ResultResponse<?> searchPatient(String param) {
        if(myDepth3Id().contains(marketingAdminId)) {
            List<MarketingManageAdminSearchPatientResponse> res = new ArrayList<>();
            List<MarketingManageAdminSearchPatientInfo> searchPatientInfo = mapper.searchPatient(param);

            for (MarketingManageAdminSearchPatientInfo patientInfo : searchPatientInfo) {
                List<MarketingManageAdminCounselHistoryInfo> searchPatientCounselingInfo = mapper.searchPatientCounselingInfo(patientInfo.getPatientId());

                MarketingManageAdminSearchPatientResponse searchPatientResponse = MarketingManageAdminSearchPatientResponse.builder()
                        .patientInfo(patientInfo)
                        .counselHistoryInfo(searchPatientCounselingInfo)
                        .build();

                res.add(searchPatientResponse);
            }

            return ResultResponse.builder()
                    .data(res)
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

//    @Transactional
//    @Override
//    public void registPatient(MarketingManageAdminRegistPatientRequest request) {
//        User user = userMapper.findById((Long)httpServletRequest.getAttribute("userId"));
//        Long hospitalId = user.getHospitalId();
//        request.setHospitalId(hospitalId);
//        try {
//            Map<String, Object> map = new HashMap<>();
//            map.put("name", request.getName());
//            map.put("phoneNumber", request.getPhoneNumber());
//            Integer cnt = mapper.checkDbData(map);
//
//            if(cnt <= 0) {
//                mapper.registPatient(request);
//            }
//            mapper.registCounselings(request);
//            mapper.registPatientCounselingInfos(request);
//            mapper.registReservationInfo(request);
//
//            Map<String, Object> paramMap = new HashMap<>();
//            paramMap.put("reservationId", request.getReservationId());
//            paramMap.put("amount", request.getAmount());
//            paramMap.put("paymentMethodId", request.getPaymentMethodId());
//            mapper.registReservationFee(paramMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public ResultResponse<?> getAccessRootList() {
        if(myDepth3Id().contains(marketingAdminId) || myDepth3Id().contains(marketingUserId)) {
            return ResultResponse.builder()
                    .data(mapper.getAccessRootList(myHospitalId()))
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

    @Override
    public ResultResponse<?> setAutoUserPatient(MarketingManageAdminSetAutoUserPatientRequest request) {
        /*
            ※ DB 자동분배 로직
            ① 당일 기준 DB 수량이 가장 적은 사람부터
            ② 고객의 상담가능일시 순으로 배분
            ③ 동일한 고객이 2개월 이내에 재방문일 경우, 동일한 상담자에게 배분처리
                - 동일한 고객이 2개월 이후 재방문했을 경우, 기존 담당자와 무관하게 DB 자동분배 로직의 ①, ② 번을 따름
                - 기존 담당자가 휴직, 퇴사 등 계정이 활성화 상태가 아닐시, DB 자동분배 로직의 ①, ②번을 따름
       */
        if(myDepth3Id().contains(marketingAdminId)) {
            List<HashMap> resChargeUserList = mapper.getDbChargeInfo(myHospitalId()); // 담당자 아이디(cnt 적은 순)
            List<Integer> patientCounselingInfosIdList = mapper.getPatientCounselingInfosIdList(myHospitalId()); // 담당자 없는 환자id

//        System.out.println("===================");
//        System.out.println(resChargeUserList);
//        System.out.println(patientCounselingInfosIdList);
//        System.out.println("===================");



//        if(patientCounselingInfosIdList.size() > 0) {
            int div = request.getDivisionCount();
            int size = patientCounselingInfosIdList.size();
            for(int i = 0; i<resChargeUserList.size(); i++) {
                for(int j = 0; j<size; j++) {
                    if(patientCounselingInfosIdList.size() == 0) break;
                    if(j >= div) break;
//                    HashMap<String, Integer> cnt = mapper.getCntTest(patientCounselingInfosIdList.get(j));
                    Map paramMap = new HashMap();
                    paramMap.put("userId", resChargeUserList.get(i).get("id"));
                    paramMap.put("patientId", patientCounselingInfosIdList.get(0));
                    mapper.insertUserPatient(paramMap);
                    patientCounselingInfosIdList.remove(0);
//                    if(cnt != null) {
//                        if (Integer.parseInt(String.valueOf(cnt.get("createdAtDiff"))) < 2) {
//                            paramMap.put("userId", cnt.get("userId"));
//                            paramMap.put("patientId", patientCounselingInfosIdList.get(j));
//                            mapper.insertUserPatient(paramMap);
//                            index++;
//                        } else {
//                            paramMap.put("userId", resChargeUserList.get(i).get("id"));
//                            paramMap.put("patientId", patientCounselingInfosIdList.get(j));
//                            mapper.insertUserPatient(paramMap);
//                            index++;
//                        }
//                    } else {
//                        paramMap.put("userId", resChargeUserList.get(i).get("id"));
//                        paramMap.put("patientId", patientCounselingInfosIdList.get(j));
//                        mapper.insertUserPatient(paramMap);
//                        index++;
//                    }
                }
            }
//        }
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> getTotalDbCount(String startDate, String endDate) {
        if(myDepth3Id().contains(marketingAdminId)) {

            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("startDate", startDate);
            paramMap.put("endDate", endDate);
            return ResultResponse.builder()
                    .data(mapper.getTotalDbCount(paramMap))
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

    @Override
    public ResultResponse<?> getReservationCount(String startDate, String endDate) {
        if(myDepth3Id().contains(marketingAdminId)) {
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("startDate", startDate);
            paramMap.put("endDate", endDate);
            return ResultResponse.builder()
                    .data(mapper.getReservationCount(paramMap))
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

    @Override
    public ResultResponse<?> getReservationFeeAmount(String startDate, String endDate) {
        if(myDepth3Id().contains(marketingAdminId)) {
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("startDate", startDate);
            paramMap.put("endDate", endDate);
            return ResultResponse.builder()
                    .data(mapper.getReservationFeeAmount(paramMap))
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

    @Override
    public ResultResponse<?> getGenderAgeCount(String startDate, String endDate) {
        if(myDepth3Id().contains(marketingAdminId)) {

            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("startDate", startDate);
            paramMap.put("endDate", endDate);
            List<HashMap> genderAgeRes = mapper.getDashboardGenderAgeCount(paramMap);
            return ResultResponse.builder()
                    .data(getGenderAgeCount(genderAgeRes))
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

    @Override
    public ResultResponse<?> getAccessRootCount(String startDate, String endDate) {
        if(myDepth3Id().contains(marketingAdminId)) {

            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("startDate", startDate);
            paramMap.put("endDate", endDate);
            return ResultResponse.builder()
                    .data(mapper.getAccessRootCount(paramMap))
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

    @Override
    public ResultResponse<?> getDbCurrentStateCount(String startDate, String endDate) {
        if(myDepth3Id().contains(marketingAdminId)) {
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("startDate", startDate);
            paramMap.put("endDate", endDate);

            return ResultResponse.builder()
                    .data(mapper.getDbCurrentStateCount(paramMap))
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

    @Override
    public ResultResponse<?> getDbChargeStateCount(String startDate, String endDate) {
        if(myDepth3Id().contains(marketingAdminId)) {
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("startDate", startDate);
            paramMap.put("endDate", endDate);
            return ResultResponse.builder()
                    .data(mapper.getDbChargeCurrentStateCount(paramMap))
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

    @Override
    public ResultResponse<?> getPatientDetail(Long id) {
        if(myDepth3Id().contains(marketingAdminId) || myDepth3Id().contains(marketingUserId)) {
            MarketingManageAdminSearchPatientInfo patientInfo = mapper.searchPatientById(id);
            List<MarketingManageAdminCounselHistoryInfo> counselHistoryInfo = mapper.searchPatientCounselingInfoById(id);
            List<MarketingManageReservationInfo> reservationInfoList = mapper.reservationInfo(patientInfo.getPatientId());
            MarketingManageAdminSearchPatientResponse marketingManageAdminSearchPatientResponse = MarketingManageAdminSearchPatientResponse.builder()
                    .patientInfo(patientInfo)
                    .counselHistoryInfo(counselHistoryInfo)
                    .reservationInfoList(reservationInfoList)
                    .build();

            return ResultResponse.builder()
                    .data(marketingManageAdminSearchPatientResponse)
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

    @Override
    public ResultResponse<?> modifyReservationStatus(MarketingManageReservationCancelRequest request) {
        if(myDepth3Id().contains(marketingAdminId) || myDepth3Id().contains(marketingUserId)) {
            mapper.modifyReservationStatus(request);
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> excelUpload(MultipartFile file) {
        if(myDepth3Id().contains(marketingAdminId)) {
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

//                guestMapper.registFile(MarketingManageGuestFileRequest.builder()
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

                        // 빈 값을 제외하고 출력
                        if (!name.isEmpty() || !phone.isEmpty() || !time.isEmpty()) {
                            checkParamMap.put("name", name);
                            checkParamMap.put("phoneNumber", phone);
                            checkParamMap.put("hospitalId", myHospitalId());
                            Integer checkPatientCnt = mapper.checkDbData(checkParamMap);
                            if(checkPatientCnt <= 0) {
                                Map<String, Object> paramMap = new HashMap<>();
                                paramMap.put("name", name);
                                paramMap.put("phoneNumber", phone);
                                paramMap.put("hospitalId", myHospitalId());
                                guestMapper.registPatiantsData(paramMap);
                            }
                            if(mapper.checkPciInfo(checkParamMap) <= 0) {
                                mapper.registPatientCounselingInfos(MarketingManageAdminRegistPatientRequest.builder()
                                        .name(name)
                                        .phoneNumber(phone)
                                        .availableTime(time)
                                        .build());
                            }
                        }
                    }

                    workbook.close();
//                fileInputStream.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
            return ResultResponse.builder()
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

//    @Override
//    public String getExcelFile() {
//        String fileName = "[정책서]_마케팅상담 GNB.xlsx";
//        return amazonS3Client.getUrl("yezak",fileName).toString();
//    }

    @Transactional
    @Override
    public ResultResponse<?> registPatientInfo(MarketingManageRegistPatientInfoRequest request) {
        if(myDepth3Id().contains(marketingAdminId) || myDepth3Id().contains(marketingUserId)) {
            request.setHospitalId(myHospitalId());

            Map<String, Object> map = new HashMap<>();
            map.put("name", request.getName());
            map.put("phoneNumber", request.getPhoneNumber());
            map.put("hospitalId", myHospitalId());

            String birth = request.getBirth().substring(0,4); // ex)19890827 => 1989
            int secondRegistrationFirstNumber = this.getSecondRegistrationFirstNumber(request.getGenderId(), Integer.parseInt(birth));
            request.setSecondRegistrationNumber(secondRegistrationFirstNumber);

            Map<String, Object> resultMap = new HashMap<>();

            if(mapper.checkDbData(map) <= 0) {
                mapper.registPatientInfo(request);
            }
            if(mapper.checkPatientCounselingInfo(request) <= 0) {
                mapper.registPatientCounselingInfo(request);
                resultMap.put("id", request.getPatientCounselingId());
            }
            return ResultResponse.builder()
                    .data(resultMap)
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

    private Integer getSecondRegistrationFirstNumber(Integer genderId ,Integer birth) {
        //        주민번호 뒷자리
        //        -- 남자
        //        1 : 1900~1999
        //        3 : 2000~
        //        9 : 1800~1899        //
        //        -- 여자
        //        2 : 1900~1999
        //        4 : 2000~
        //        0 : 1800~1899
        int result = 0;
        if(genderId == 1) { // 남자
            if(1900 <= birth && 1999 >= birth) {
                result = 1;//1
            }
            if(2000 <= birth) {
                result = 3;//3
            }
            if(1800 <= birth && 1899 >= birth) {
                result = 9;//9
            }
        }
        if(genderId == 2) { // 여자
            if(1900 <= birth && 1999 >= birth) {
                result = 2;//2
            }
            if(2000 <= birth) {
                result = 4;//4
            }
            if(1800 <= birth && 1899 >= birth) {
                result = 0;//0
            }
        }
        return result;
    }

    @Override
    public ResultResponse<?> registCounselingInfo(MarketingManageRegistCounselingInfoRequest request) {
        if(myDepth3Id().contains(marketingAdminId) || myDepth3Id().contains(marketingUserId)) {
            request.setUserId(myUserId());
            mapper.registCounselingInfo(request);
            return ResultResponse.builder()
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

    @Transactional
    @Override
    public ResultResponse<?> registReservationInfo(MarketingManageRegistReservationInfoRequest request) {
        if(myDepth3Id().contains(marketingAdminId) || myDepth3Id().contains(marketingUserId)) {
            mapper.registReservationInfo(request);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("reservationId", request.getId());
            paramMap.put("amount", request.getAmount());
            paramMap.put("paymentMethodId", request.getPaymentMethodId());
            mapper.registReservationFee(paramMap);
            return ResultResponse.builder()
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

    @Transactional
    @Override
    public ResultResponse<?> modifyPatientInfo(MarketingManageModifyPatientInfoRequest request) {
        if(myDepth3Id().contains(marketingAdminId) || myDepth3Id().contains(marketingUserId)) {
            request.setHospitalId(myHospitalId());

            if(request.getBirth() != null) {
                String birth = request.getBirth().substring(0,4); // ex)19890827 => 1989
                int secondRegistrationFirstNumber = this.getSecondRegistrationFirstNumber(request.getGenderId(), Integer.parseInt(birth));
                request.setSecondRegistrationNumber(secondRegistrationFirstNumber);
            }

            mapper.modifyPatientInfo(request);
            mapper.modifyPatientCounselingInfo(request);
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

//    @Override
//    public List<MarketingManageHospitalProductInfo> getHospitalProductList(int hospitalId) {
//        return mapper.getHospitalProductList(hospitalId);
//    }

    private MarketingManageAdminDashboardGenderAgeCount getGenderAgeCount(List<HashMap> param) {

        Map<String, Integer> genderAgeCountMap = new HashMap<>();

        for (HashMap<String, Object> entry : param) {
            String ageRangeStr = entry.get("ageRange").toString();
            String genderStr = entry.get("gender").toString();
            Integer count = Integer.parseInt(entry.get("count").toString());

            String key = ageRangeStr + "-" + genderStr;
            genderAgeCountMap.put(key, count);
        }

        List<Integer> man = new ArrayList<>();
        man.add(getCountFromMap(genderAgeCountMap, "0-14-남자"));
        man.add(getCountFromMap(genderAgeCountMap, "15-24-남자"));
        man.add(getCountFromMap(genderAgeCountMap, "25-34-남자"));
        man.add(getCountFromMap(genderAgeCountMap, "35-44-남자"));
        man.add(getCountFromMap(genderAgeCountMap, "45-54-남자"));
        man.add(getCountFromMap(genderAgeCountMap, "55-64-남자"));
        man.add(getCountFromMap(genderAgeCountMap, "65+-남자"));
        List<Integer> woman = new ArrayList<>();
        woman.add(getCountFromMap(genderAgeCountMap, "0-14-여자"));
        woman.add(getCountFromMap(genderAgeCountMap, "15-24-여자"));
        woman.add(getCountFromMap(genderAgeCountMap, "25-34-여자"));
        woman.add(getCountFromMap(genderAgeCountMap, "35-44-여자"));
        woman.add(getCountFromMap(genderAgeCountMap, "45-54-여자"));
        woman.add(getCountFromMap(genderAgeCountMap, "55-64-여자"));
        woman.add(getCountFromMap(genderAgeCountMap, "65+-여자"));

        MarketingManageAdminDashboardGenderAgeCount marketingManageAdminDashboardGenderAgeCount = MarketingManageAdminDashboardGenderAgeCount.builder()
//                .man0to14(getCountFromMap(genderAgeCountMap, "0-14-남자"))
//                .woman0to14(getCountFromMap(genderAgeCountMap, "0-14-여자"))
//                .man15to24(getCountFromMap(genderAgeCountMap, "15-24-남자"))
//                .woman15to24(getCountFromMap(genderAgeCountMap, "15-24-여자"))
//                .man25to34(getCountFromMap(genderAgeCountMap, "25-34-남자"))
//                .woman25to34(getCountFromMap(genderAgeCountMap, "25-34-여자"))
//                .man35to44(getCountFromMap(genderAgeCountMap, "35-44-남자"))
//                .woman35to44(getCountFromMap(genderAgeCountMap, "35-44-여자"))
//                .man45to54(getCountFromMap(genderAgeCountMap, "45-54-남자"))
//                .woman45to54(getCountFromMap(genderAgeCountMap, "45-54-여자"))
//                .man55to64(getCountFromMap(genderAgeCountMap, "55-64-남자"))
//                .woman55to64(getCountFromMap(genderAgeCountMap, "55-64-여자"))
//                .man65over(getCountFromMap(genderAgeCountMap, "65+-남자"))
//                .woman65over(getCountFromMap(genderAgeCountMap, "65+-여자"))
                .man(man)
                .woman(woman)
                .build();
        return marketingManageAdminDashboardGenderAgeCount;
    }

    private Integer getCountFromMap(Map<String, Integer> map, String key) {
        return map.getOrDefault(key, 0);
    }


}
