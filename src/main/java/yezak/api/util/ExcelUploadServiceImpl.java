package yezak.api.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.management_support.attendance.attendance.dto.CreateAttendanceDto;
import yezak.api.global.common.ResultResponse;
import yezak.api.api.management_support.attendance.attendance.AttendanceMapper;

import java.io.IOException;
import java.time.LocalDate;

import static yezak.api.config.MyIdConfig.myHospitalId;


@RequiredArgsConstructor
@Component
@Service
@Slf4j
public class ExcelUploadServiceImpl implements ExcelUploadService {
    private final AttendanceMapper attendanceMapper;

    @Override
    public ResultResponse<?> uploadExcelFile(MultipartFile file) {
        try {
            // 엑셀 파일 읽고 첫번째 시트 가져오기
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            //날짜 및 시간 형식 지정을 위해 패턴 설정
            Cell cell = sheet.getRow(4).getCell(1);
            //String 11
            String monthString = cell.getStringCellValue().replace("월", "").trim();

            for (int i = 4; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                // 두 번째 행은 구분 및 출퇴근 고정으로 건너뛰기
                if (i == 5) {
                    continue;
                }

                // 직원 이름 가져오기
                String name = row.getCell(1).getStringCellValue();

                // 출퇴근 시간 처리
                for (int j = 2; j < row.getLastCellNum(); j +=2) {

                    Cell dayCell = sheet.getRow(4).getCell(j);
                    String dayCellValue = dayCell.getStringCellValue();
                    String dayString = dayCellValue.substring(0, dayCellValue.length()-3).trim();


                    int defaultYear = LocalDate.now().getYear();
                    String dateString = defaultYear + "-" + monthString + "-" + dayString;

                    Cell commutingTimeCell = row.getCell(j);
                    Cell leavingTimeCell = row.getCell(j+1);

                    if (commutingTimeCell == null || leavingTimeCell == null ||
                            "-".equals(commutingTimeCell.getStringCellValue()) ||
                            "-".equals(leavingTimeCell.getStringCellValue())) {
                        continue;
                    }

                    String commutingTimeString = row.getCell(j).toString();
                    String leavingTimeString = row.getCell(j + 1).toString();

                    if (commutingTimeString.length() == 4) {
                        commutingTimeString = "0" + commutingTimeString;
                    }
                    if (leavingTimeString.length() == 4) {
                        leavingTimeString = "0" + leavingTimeString;
                    }

                    // 출퇴근 시간이 비어 있거나 '-'인 경우 건너뜀
                    if (commutingTimeString.isEmpty() || leavingTimeString.isEmpty() || "-".equals(commutingTimeString) || "-".equals(leavingTimeString)) {
                        continue;
                    }

                    // 날짜와 시간을 변환

                    CreateAttendanceDto createAttendanceDto = new CreateAttendanceDto(name, dateString, commutingTimeString, leavingTimeString, myHospitalId());

                    attendanceMapper.createAttendance(createAttendanceDto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("등록되었습니다.")
                .build();
    }
}
