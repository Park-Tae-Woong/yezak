package yezak.api.api.admin.audit_record.login_log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.admin.audit_record.login_log.dto.LoginLogListReq;
import yezak.api.api.admin.audit_record.login_log.dto.LoginLogRes;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.Page;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
@Transactional
public class LoginLogServiceImpl implements LoginLogService{
    private final LoginLogMapper loginLogMapper;
    private final Integer loginLogId = 71 ;
    @Override
    public ResultResponse<?> getLoginLogList(String start, String end, Integer pageNum, Integer pageSize) {
        if (myDepth3Id().contains(loginLogId)) {
            Integer offset = (pageNum - 1) * pageSize;
            LoginLogListReq loginLogListReq = LoginLogListReq.builder()
                    .end(end)
                    .start(start)
                    .hospitalId(myHospitalId())
                    .offset(offset)
                    .pageSize(pageSize)
                    .build();

            Integer totalCount = loginLogMapper.countLoginLogList(loginLogListReq);
            Page page = Page.builder()
                    .pageNum(pageNum)
                    .pageSize(pageSize)
                    .totalCount(totalCount)
                    .totalPage((int) Math.ceil((double)totalCount / pageSize))
                    .build();

            return ResultResponse.builder()
                    .data(loginLogMapper.getLoginLogList(loginLogListReq))
                    .page(page)
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
    public void loginLogExcel(HttpServletResponse response, String start, String end) throws IOException {
        LoginLogListReq loginLogListReq = LoginLogListReq.builder()
                .end(end)
                .start(start)
                .hospitalId(myHospitalId())
                .build();

        List<LoginLogRes> loginLogResList = loginLogMapper.loginLogExcel(loginLogListReq);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("로그인 로그");
        Row row;
        Cell cell;
        int rowNum = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        CellStyle style = workbook.createCellStyle();

        //가운데정렬
        style.setAlignment(HorizontalAlignment.CENTER);

        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("사용자계정");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("수행업무");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("결과");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("결과사유");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("일시");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("IP주소");
        cell.setCellStyle(style);

        //열 너비
        sheet.setColumnWidth(0, 5000);
        sheet.setColumnWidth(3, 5000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 7000);

        for (LoginLogRes loginLogRes : loginLogResList) {
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue(loginLogRes.getEmail());

            cell = row.createCell(1);
            cell.setCellValue(loginLogRes.getDistinction());

            cell = row.createCell(2);
            cell.setCellValue(loginLogRes.getResult());

            cell = row.createCell(3);
            cell.setCellValue(loginLogRes.getMessage());

            cell = row.createCell(4);
            cell.setCellValue(loginLogRes.getCreatedAt().format(formatter));

            cell = row.createCell(5);
            cell.setCellValue(loginLogRes.getIp());
        }

        //엑셀형식 데이터
        response.setContentType("application/vnd.ms-excel; charset=euc-kr");
        //다운로드 방법, 파일명
        response.setHeader("Content-Disposition", "attachment;filename=login_log.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();

    }

}
