package yezak.api;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import kr.or.nhic.api.services.nhic.NhicLocator;
//import kr.or.nhic.api.services.nhic.NhicPort;
//import kr.or.nhic.api.services.nhic.NhicPortStub;
//import kr.or.nhic.api.services.nhic.NhicTestCase;
//import nhic.ws.common.Constants;
//import org.apache.axis.AxisFault;
import org.junit.jupiter.api.Test;
//import org.opentest4j.AssertionFailedError;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.util.AssertionErrors.assertNotNull;
//
//import vo.maint.yos.wg.nhmp.Msg1Vo;
//import vo.maint.yos.wg.nhmp.Msg2Vo;
//import vo.maint.yos.wg.nhmp.Msg3Vo;
//import vo.maint.yos.wg.nhmp.Msg4Vo;
//import vo.maint.yos.wg.nhmp.Msg5Vo;
//import vo.maint.yos.wg.nhmp.Msg6Vo;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.xml.namespace.QName;
//import javax.xml.rpc.Service;
//import javax.xml.rpc.ServiceException;


@SpringBootTest
class ApiApplicationTests {

	@Test
	void test20230810_2() {

//		Msg1Vo vo = new Msg1Vo();
//		vo.setSujinjaJuminNo("0001013123454");
//		vo.setYkiho("41300033");
//		vo.setDate("20070421-133720");
//		vo.setClientInfo("192.168.0.243");
//		vo.setOperatorJuminNo("1182372273264");
//		vo.setPgmType("3");
//		vo.setVersion("1.2.34");
//		vo.setMsgType("M1");
//
//		Msg1Vo [] msg1Arr = new Msg1Vo[1];
//		msg1Arr[0] = vo;
//
//		try {
////            NhicLocator locator = new NhicLocator("http://api.nhic.or.kr:1443/services/nhic?WSDL");
//			NhicLocator locator = new NhicLocator("http://ws.nhic.or.kr:1442/services/nhic?WSDL");
//			NhicPort port = (locator).getnhicPort();
//
////		String [] result = port.test("test");
//			Msg2Vo[] result = port.requestQualification(vo);
////		Msg2Vo [] result = port.requestQualificationList(msg1Arr);
////		Msg4Vo [] result = port.requestApprovalList(null);
////		Msg6Vo [] result = port.cancelApprovalList(null);
////		boolean result = port.getisKJCall();
////		String result = port.doNotUseThisMethod(vo);
////		Msg4Vo result = port.requestApproval(new Msg3Vo());
////		Msg6Vo result = port.cancelApproval(new Msg5Vo());
////		port.setisKJCall(true);
//			System.out.println("=============");
////        if(result) {
//			if(result == null) {
//				System.out.println("result---null");
//			} else {
//				System.out.println(result);
//			}
//			System.out.println("=============");
//		} catch (ServiceException | RemoteException e) {
//			throw new RuntimeException(e);
//		}

	}

	@Test
	void test() {
		try {

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\hira\\DDMD\\sam\\in\\20230620test7.B01"), "euc-kr"));

			//		청구서 정보
			writer.write("090");            //	- 청구서 서식버전(3) : 090
			writer.write("090");            //	- 명세서 서식버전(3) : 090
			writer.write("2023060011");        //	- 청구번호(10) : 2023050011(yyyymm+해당진료년월에발생한청구서일련번호4자리)
			writer.write("H010");            //	- 서식번호(4) : H010(H010:건강보험, 요양급여비용, 심사청구서 / H011:의료급여비용, 심사청구서)
			writer.write("12333573");        //	- 요양(의료급여)기관기호(8) : 12333573
			writer.write("1");                //	- 수신기관(1) : 1 -- 고정값(1로기재)
			writer.write("4");                //	- 보험자종별구분(1) : 보험자종별구분 - 4:건강보험, 7:보훈위탁진료 요양기관의 상이처·무자격자 또는 보훈병원의 국비일반(상이처·무자격자), 국비보험(급여) 2차 / 의료급여진료구분 - 1:1차진료, 2:2차진료(3차포함)
			writer.write(" ");                //	- 청구구분(1) : null (1:보완청구 / 2:추가청구)
			writer.write("0");                //	- 청구단위구분(1) : 0 (1~6:해당 주단위 / 0:월단위 통합청구)
			writer.write("1");                //	- 진료구분(1) : 1 (1:의과 / 2:치과 / 5:의료급여정액 / 7:보건기관 / 8:약국 / 9:한방)
			writer.write(" ");                //	- 진료분야구분(1) : null (1:내과분야 / 2:외과분야 / 3:신*소아청소년과분야 / 4:안*이비인후과분야 / 5:피부*비뇨의학과분야 / 6:치과 / 9:한의과)
			writer.write("2");                //	- 진료형태(1) : 2 ( 1: 의과입원, 보건기관입원
			// 2: 의과외래, 보건기관외래
			// 3: 치과입원, 의료급여정신건강의학과정액 입원
			// 4: 치과외래, 의료급여정신건강의학과정액 외래
			//(의료급여정신건강의학과 정액외래는 2017.3.12. 진료분까지 해당)
			// 5: 의료급여정신건강의학과정액 낮병동
			// 6: 약국처방조제
			// 7: 약국직접조제
			// 8: 한방입원
			// 9: 한방외래
			// A: 요양병원 장기환자입원(의과)
			// B: 호스피스 정액 입원(의과)
			// C: 가정형 호스피스 외래(의과)
			// D: 권역외상센터 입원
			// E: 권역외상센터 외래
			// H: 의료급여혈액투석정액 외래)
			writer.write("202305");            //	- 진료년월(6) : 202305 (yyyymm / 입원의 경우 퇴원일이 속한 월을 기재)
			writer.write(leftPadding(1+"", "0", 6));//		- 건수(6) : 000001 (요양급여비용 명세서의 청구건수를 합하여 기재)
			writer.write(leftPadding(352650+"", "0", 12));            //	- 요양급여비용총액1(12) : 352,650
			writer.write(leftPadding(95500+"", "0", 12));            //		- 본인일부부담금(12) : 95,500
			writer.write(leftPadding(0+"", "0", 12));            //		- 본인부담상한액초과금(12) : 0
			writer.write(leftPadding(257150+"", "0", 12));            //		- 청구액(12) : 257,150
			writer.write(leftPadding(0+"", "0", 12));            //		- 지원금(12) : 0
			writer.write(leftPadding(0+"", "0", 12));            //		- 장애인의료비(12) : 0	( - 의료급여의 경우 의료급여 2종 장애인 의료급여비용명세서의 장애인의료비를 합하여 기재
			//- 건강보험의 경우 차상위 장애인 만성질환․18세미만 본인부담경감 대상자 요양급여비용 명세서의 장애인의료비를 합하여 기재)
			writer.write(leftPadding(352650+"", "0", 12));    //	- 요양급여비용총액2, 진료비총액(12) : 352,650	( - 요양급여비용총액 2: 요양급여비용 명세서의 요양급여비용총액 2를 합하여 기재
			//	- 진료비총액: 보훈국비환자의 경우 요양급여비용 명세서의 진료비 총액을 합하여 기재)
			writer.write(leftPadding(0+"", "0", 12));        //		- 보훈청구액(12) : 0		 다음의 보훈국비환자 또는 보훈감면환자일 경우에 한하여 요양급여비용 명세서의 보훈청구액을 합하여 기재
			//- 보훈위탁진료 요양기관(동일한 기관에서 발행한 처방전에 따라 조제하는 약국)의 보훈국비환자 진료분(처방조제 분)인 경우
			//- 보훈병원의 국비일반(상이처, 무자격자), 국비보험(급여) 2차 명세서인 경우
			//- 보훈병원(동일한 기관에서 발생한 처방전에 따라 조제하는 약국)의 보훈감면환자 진료분(처방조제분)인 경우
			writer.write(leftPadding(0+"", "0", 12));        //		- 100/100본인붐담금총액(12) : 0
			writer.write(leftPadding(0+"", "0", 12));        //		- 보훈 본인일부부담금(12) : 0
			writer.write(leftPadding(0+"", "0", 12));        //		- 100/100미만 총액(12) : 0
			writer.write(leftPadding(0+"", "0", 12));        //		- 100/100미만 본인일부부담금(12) : 0
			writer.write(leftPadding(0+"", "0", 12));        //		- 100/100미만 청구액(12) : 0
			writer.write(leftPadding(0+"", "0", 12));        //		- 100/100미만 보훈청구액(12) : 0

			//		============================ 차등수가 적용구분 start

			writer.write("000000");//		- 진료(조제)일수(4.2) : 0000.00		( - 1개월 또는 1주일동안 의사(약사)별 실제 진료(조제)한 일수[차등수가 미적용 진료(조제)일 제외]의 합을 기재하되, 소수점 첫째자리에서 절사하여 기재
			// ※ 단, 주 3일이상이면서 20시간이상 근무하는 격일제, 시간제 근무자는 1개월(주단위청구의 경우 1주일) 동안 재직한 일수의 1/2로 산정(소수점이하 4사5입)하되, 최대 월15일(주단위청구의경우 주3일)을 초과할 수 없음
			//- 이 경우 별표 8. 특정내역 구분코드의 해당 특정내역(MT008) 기재형식에 따라 첫번째 명세서에 의사(약사)별 진료(조제)일수를 기재
			writer.write("0000");//		- 의(약)사수(2.2) : 00.00 (사용유보)
			writer.write("00000000");//		- 차등지수(1.7) : 0.0000000
			// (상대가치점수표 제1부 일반원칙 Ⅲ.차등수가. 다항에 따라 월단위 또는 주단위로 산정된 차등지수를 소수점 여덟째 자리에서 4사5입하여 기재하며, 1일 평균 진찰횟수(약사의 경우는 조제횟수)는
			//총 진찰(조제)횟수[차등수가 미적용 진찰료(조제료 등) 제외]에서 의사, 치과의사, 한의사, 약사가 진료(조제)한 총 일수로 나누어 계산하되, 소수점 첫째자리에서 절사하여 산정)
			writer.write(leftPadding(0+"", "0", 12));//		- 차등수가 청구액(12) : 0	(차등수가청구액은 각각의 명세서별로 진찰료[약국은 조제료, 약국 관리료, 조제기본료, 복약지도료(이하 “조제료 등”이라 한다)]를 차등 산정한 청구액을 합하여 기재
			//단, 차등수가 미적용 진찰료(조제료 등) 제외
			//▪의원(치과의원, 한의원, 보건의료원)
			// 차등수가청구액 = [청구액 - {진찰료(차등수가 미적용 진찰료 제외) × (1-차등지수)}]
			//▪약국
			// 차등수가청구액 = [청구액 - {조제료 등(차등수가 미적용 조제료 등 제외) × (1-차등지수)}]

			//		============================ 차등수가 적용구분 end

			writer.write(LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd")).toString());//		- 청구일자(8) : 20230613 (yyyymmdd)
			writer.write(rightPadding("문일", " ", 20));    //		- 청구인(20) : 문일 (요양기관 대표자or개설자 성명 한글or영문 / 한글 각2 byte ex.문일 : 4byte)
			writer.write(rightPadding("문일", " ", 20));    //		- 작성자성명(20) : 문일 (한글 각2 byte ex.문일 : 4byte)
			writer.write(rightPadding("691212", "0", 13));        //		- 작성자생년월일(13) : 6912120000000
			writer.write(rightPadding("B01001132022102005048019720223"," ",35));//		- 검사승인번호(35) : B01001132022102005048019720223 (심사평가원에서 검사 인증한 프로그램의 승인번호를 기재)
			writer.write(rightPadding("", " ", 5));//		- 대행청구단체기호(5) : null (의약단체에서 심사청구를 대행하는 경우 대행청구단체의 기호(5자리)를 기재 / 이 경우 기호는 제15조제2항에 따라 대행청구통지를 받은 심사평가원장이 부여한 기호를 말한다)
			writer.write(rightPadding("", " ", 1750));//		- 참조란(1750) : null (추가기술사항을 기재)

			////////////////////////////////////////////////////////////////////////////////////////////////////////
			writer.write("\r\n");
			////////////////////////////////////////////////////////////////////////////////////////////////////////


			// 요양급여비용(의료급여비용) 명세서 - 의치과 start
			// for start

			//일반내역 start
			writer.write("2023050011"); //청구번호(10) : 2023050011
			// if no >= 100000 : "A" + leftPadding((no - 100000)+"","0",4);
			int no = 1;
			if(no >= 100000) {
				writer.write("A" + leftPadding((no - 100000)+"","0",4));
			} else {
				writer.write(leftPadding(no+"","0",5));
			}//명세서일련번호(5) : 00001 (요양급여비용 명세서의 일련번호로 아래 예시와 같이 5자리
			//숫자로 순차적으로 기재하되 반드시 00001부터 연이어 기재
			//단, 100,000번째부터는 A0000부터 연이어 기재
			//▪유형
			//1. 50번째 명세서인 경우 다음과 같이 기재
			//- 00050
			//2. 100,500번째 명세서인 경우 다음과 같이 기재
			//- A0500

			writer.write("A"); //내역구분(1) : A - 일반내역 -- 하드코딩
			writer.write("H021"); // 서식번호(4) : H020: 건강보험 의과 입원 요양급여비용 명세서
			//			H120: 건강보험 의과 입원 요양급여비용 명세서(요양병원 장기
			//					환자)
			//			H021: 건강보험 의과 외래 요양급여비용 명세서
			//			H022: 건강보험 치과 입원 요양급여비용 명세서
			//			H023: 건강보험 치과 외래 요양급여비용 명세서
			//			H030: 의료급여 의과 입원 명세서
			//			H130: 의료급여 의과 입원 명세서(요양병원 장기 환자)
			//			H031: 의료급여 의과 외래 명세서
			//			H032: 의료급여 치과 입원 명세서
			//			H033: 의료급여 치과 외래 명세서
			writer.write("12333573"); // 요양기관(의료급여기관)기호 (8) : 요양급여비용 심사청구서와 동일
			writer.write(rightPadding(""," ",11)); // 보장기관기호(11) : 의료급여를 받는 수급권자의 관할 시․군․구 기호를 기재
			writer.write(rightPadding(""," ",1)); // 의료급여종별구분(1)  : 의료급여종별 구분 기재
//																					▪의료급여종별구분
//																							1: 1종 2: 2종 4: 행려
//																							6: 2종 장애인의 2차의료급여
//																							8: 2종 장애인의 1차의료급여
//																							N: 노숙인 1종
			writer.write(rightPadding(""," ",1)); // 공상 등 구분(1) : ▪공상 등 구분
//																					0: 무 1: 공상
//																					4: 보훈위탁진료 요양기관의 보훈국비환자(건강보험 또는 의료
//																							급여 수급권자)
//																					7: 보훈위탁진료 요양기관의 보훈국비환자(상이처, 무자격자)
//																					8: 군인가족, 예비역장군 및 대령, 창군 및 6·25참전요원의 군
//																					요양기관 이용시
//																					9: 군인, 군무원의 군 요양기관 이용시
//																					B: 보훈병원의 국비일반(상이처, 무자격자) 또는
//																					국비보험(급여) 1차
//																					C: 차상위 희귀질환·중증난치질환 또는 중증질환 본인부담경감
//																					대상자
//																					D: 보훈병원의 국비보험(급여) 2차
//																					E: 차상위 만성질환·18세미만 본인부담경감대상자
//																					F: 차상위 장애인 만성질환·18세미만 본인부담경감대상자
//																					G: 긴급복지 의료지원대상자
//																					H: 희귀질환 지원대상자
			writer.write("9"); // 정액,정률구분(1) : - 읍․면 소재 병원 및 종합병원의 건강보험 외래 요양급여비용 명세서를 월단위로 통합하여 작성하는 경우 정액, 정률 여부를 기재(2007.7.31. 진료분까지 해당)
//														0: 정액 9: 정률
//													- 요양병원 장기환자 입원요양급여비용 명세서의 경우 정액수가 적용과 행위별수가 적용(특정기간) 여부를 기재
//														1: 정액수가 적용 2: 행위별수가 적용(특정기간
			//일반내역 - 청구구분 start
			writer.write(rightPadding(""," ",1)); // 청구구분-코드(1) : 1:보완청구 / 2:추가청구 / 3:분리청구
			writer.write(rightPadding(""," ",7)); // 청구구분-접수번호(7) : 보완청구, 추가청구, 입원 요양급여비용 분리청구의 경우 당초 청구한 명세서의 접수번호를 기재. 단, 보훈병원 국비보험(급여) 2차명세서의 경우 1차명세서 접수번호를 기재
			writer.write(rightPadding(""," ",5)); // 청구구분-명세서일련번호(5) : 보완청구, 추가청구, 입원 요양급여비용 분리청구의 경우 당초 청구한 명세서의 일련번호를 기재. 단, 보훈병원 국비보험(급여) 2차명세서의 경우 1차명세서 일련번호를 기재
			writer.write(rightPadding(""," ",2)); // 청구구분-사유코드(2) : 보완청구의 경우 당초 청구한 명세서의 심사불능 사유를 기재
			writer.write(rightPadding(""," ",8)); // 청구구분-최초입원개시일(8) :  입원 요양급여비용 분리청구의 경우 최초입원개시일을 기재 ▪유형: CCYYMMDD
			//일반내역 - 청구구분 end

			writer.write(rightPadding("박상호"," ",20)); // 가입자(세대주)성명(20) : 건강보험의 경우 가입자 성명, 의료급여는 세대주 성명을 한글 또는 영문으로 기재
			writer.write(rightPadding("80962768156"," ",20)); // 증번호(보장시설 및 노숙인시설기호) (20) :  건강보험은 증번호를 기재하고, 보장시설에 입소해 있는 의료급여 환자 또는 노숙인 의료급여환자가 진료를 받은 경우 보장기관 (시․군․구)에서 부여한 보장시설기호 또는 노숙인시설기호를 기재
			writer.write(rightPadding("박상호"," ",20)); // 수진자성명(20)
			writer.write(rightPadding("8509271081113"," ",13)); // 수진자주민등록번호(13) : 주민등록번호 생년월일 다음의 "-" 는 제외
			writer.write(leftPadding("1","0",3)); // 요양급여일수(3) :  해당 요양급여비용 명세서에서 요양급여를 받은 실 일수를 기재 하되, 입원 또는 내원일수에 원내투약일수를 산입하여 기재. 이때 내원 또는 입원일수와 투약일수가 중복될 때에는 1일로 계산함
			writer.write(leftPadding("1","0",3)); // 입원일수, 총내원일수(3) :  입원 또는 내원하여 진료를 받은 실 일수를 기재
			writer.write(rightPadding("", " ", 31)); // 공란(31)
			writer.write(rightPadding("", " ", 2)); // 입원경로(2) : 병원급이상 입원 환자의 경우 요양기관 도착경로와 입원경로를  조합하여 기재
//																					▪도착경로 - 1: 타요양기관경유 2: 응급구조대후송 3: 기타
//																					▪입원경로 - 1: 응급실 2: 외래
			writer.write("1"); // 진료결과(1) :  요양급여비용 명세서상 최종 진료일의 환자상태를 구분하여 기재 - ▪진료결과 1: 계속 / 2: 이송 / 3: 회송 / 4: 사망 / 9: 퇴원 또는 외래 치료종결
			writer.write(leftPadding(21620+"", "0", 10)); //요양급여비용총액1(10) : 기본 진료료, 약제 등 요양기관 종별가산율이 적용되지 않는 요양 급여비용, 요양기관 종별가산율이 적용되는 진료행위료와 가산금액을
//																								모두 합하여 총 금액에서 10원 미만 절사한 금액을 기재하되, 100 분의100미만 총액, 100분의100본인부담 및 비급여를 제외한 총 금액을 기재
//																								단, 보훈병원의 국비일반(상이처, 무자격자) 또는 국비보험(급여) 2차명세서의 경우 보훈병원 의료수가를 적용한 총 금액 및 가산금액을 모두 합하여 기재 (※ 국비가산적용 이전 금액임)
			writer.write(leftPadding(6400+"", "0", 10)); // 본인일부부담금(10)
			// - ｢국민건강보험법 시행령｣ 별표 2 및 같은 법 시행규칙 별표 3에 따른 본인일부부담금(같은 법 시행령 별표 2 제4호 및 제6호에 따른 금액을 제외)을 기재
//			- 본인일부부담금은 100원 미만 절사한 금액으로 기재하되, 입원 진료의 경우에는 10원 미만 절사한 금액으로 기재(단, 상급종합 병원, 종합병원, 병원, 치과병원 및 요양병원의 2009.6.30. 이전 진료분 까지는 10원 미만 절사한 금액으로 기재)
//			․ 본인부담정액제에 해당하는 경우는 정액 본인일부부담금을 기재
//			․ ｢국민건강보험법 시행령｣ 제19조에 따른 본인부담상한액초과금이 발생한 경우는 실제 본인이 부담하는 금액과 본인부담상한액 초과금을 합하여 기재
//			․ 희귀질환 지원대상자의 경우는 ‘희귀질환자 의료비지원사업’에 따른 해당 지원대상자에게 지원하는 비용을 포함하여 기재
//			․ 차상위 장애인 만성질환․18세미만 본인부담경감대상자의 경우는 ｢국민건강보험법 시행령｣ 별표 2에 따른 본인부담액 중 ‘장애인 복지사업’에서 지원하는 장애인의료비를 제외한 금액을 기재
//			․ 긴급복지 의료지원대상자가 본인부담상한액초과금이 발생한 경우는 실제 본인이 부담하는 금액, ‘긴급지원사업’에서 지원 하는 지원금 및 본인부담상한액초과금을 합하여 기재(건강보험 100분의100본인부담금 제외)
//			· 잠복결핵감염 검진비지원대상은 ‘잠복결핵감염 검진비지원사업’ 에서 지원하는 지원금을 기재(건강보험 100분의100본인부담금 제외)
//			․ 보훈국비환자 또는 보훈감면환자의 경우에는 ｢국가보훈대상자 의료지원에 관한 규칙｣에 따른 본인일부부담금을 기재하고, 보훈 병원 국비보험(급여) 2차명세서의 경우 1차명세서 심결 본인일부 부담금을 기재

			writer.write(leftPadding(0+"","0",10)); // 본인부담상한액초과금(10) :
//			「국민건강보험법 시행령｣ 제19조제6항에 따라 입원기간 내에 본인 일부부담금의 총액(법 제44조제2항 및 영 제19조제3항에 따른 금액)이 같은 법 시행령 별표 3에 따라 산정한 본인부담상한액의
//			최고금액을 넘는 경우에는 그 초과금액(본인부담상한액을 확인할 수 있는 경우에는 그 초과금액)을 기재하며, 입원건의 분리 또는 추가청구시에는 원청구와 연계하여 초과한 금액을 기재. 이 경우
//			다른 법령에 따라 국가나 지방자치단체로부터 받는 의료비 지원금 (요양급여비용 명세서에 기재하여야 하는 지원금에 한한다)을 제외 하고 본인부담상한액초과금을 기재

			writer.write(leftPadding(15220+"","0",10)); // 청구액(10) :
			// 요양급여비용총액 1에서 ｢국민건강보험법 시행령｣ 별표 2 및 같은 법 시행규칙 별표 3에 따른 본인일부부담금을 제외한 금액을 기재하며, 차상위 장애인 만성질환․18세미만 본인부담경감대상자의 경우는 요양급여비용총액 1에서 본인일부부담금과 장애인의료비를 제외한 금액을 기재
			// 단, 보훈위탁진료 요양기관의 보훈국비환자 중 상이처, 무자격자의 경우 ‘0’으로 기재하고, 보훈병원 국비보험(급여) 2차명세서의 경우 1차명세서 심결공단부담금을 기재하며, 보훈병원 보훈감면환자의 경우 요양급여비용총액 1에서 본인일부부담금과 보훈청구액을 제외한 금액을 기

			writer.write(leftPadding(0+"", "0", 10)); // 지원금(10)
//			- 희귀질환 지원대상자의 경우 ‘희귀질환자 의료비지원사업’에 따른 해당 지원대상자에게 지원하는 비용을 기재
//			- 긴급복지 의료지원대상자의 경우 ‘긴급지원사업’에 따른 해당 지원대상자에게 지원하는 비용을 기재
//			- 잠복결핵감염 검진비지원대상은 ‘잠복결핵감염 검진비지원사업’ 에서 지원하는 금액을 10원 미만 절사하여 기재

			writer.write(leftPadding(0+"","0",10)); // 장애인의료비(10)
			writer.write(leftPadding(0+"","0",10)); // 대불금(10)
			writer.write(leftPadding(21620+"","0",10)); // 요양급여비용총액2, 진료비총액(10)
			writer.write(leftPadding(0+"","0",10)); // 보훈청구액(10)
			writer.write(leftPadding(0+"","0",10)); // 공란(10)
			writer.write(leftPadding(21620+"","0",10)); // 공란(10)
			writer.write(leftPadding(0+"","0",10)); // 건강보험(의료급여)100분의100본인부담금총액(10)
			writer.write(leftPadding(0+"","0",10)); // 보훈본인일부부담금(10)
			writer.write(leftPadding(0+"","0",10)); // 100분의100미만총액(10)
			writer.write(leftPadding(0+"","0",10)); // 100분의100미만본인일부부담금(10)
			writer.write(leftPadding(0+"","0",10)); // 100분의100미만청구액(10)
			writer.write(leftPadding(0+"","0",10)); // 100분의100미만보훈청구액(10)
			//일반내역end

			/////////////////////////////////////////////////////////////////////////////
			writer.write("\r\n");
			/////////////////////////////////////////////////////////////////////////////

			//상병내역start
			//for start
			writer.write("2023050011"); //청구번호(10) : 2023050011
			// if no >= 100000 : "A" + leftPadding((no - 100000)+"","0",4);
			int no2 = 1;
			if(no2 >= 100000) {
				writer.write("A" + leftPadding((no2 - 100000)+"","0",4));
			} else {
				writer.write(leftPadding(no2+"","0",5));
			}//명세서일련번호(5) : 00001 (요양급여비용 명세서의 일련번호로 아래 예시와 같이 5자리
			//숫자로 순차적으로 기재하되 반드시 00001부터 연이어 기재
			//단, 100,000번째부터는 A0000부터 연이어 기재
			//▪유형
			//1. 50번째 명세서인 경우 다음과 같이 기재
			//- 00050
			//2. 100,500번째 명세서인 경우 다음과 같이 기재
			//- A0500

			writer.write("B"); // 내역구분(1) : B-상병내역 -- 고정값같음(하드코딩)
			writer.write("1"); // 상병분류구분(1) :
//			- 각 상병분류기호별 주․부상병, 배제된 상병을 구분하는 구분자 로서 상병분류기호별로 반드시 해당 구분자를 기재
//			- 구분코드 ‘1’(주상병)은 ‘상병분류기호’ 첫 번째 자리(제1단)의 상병에만 기재
//			▪구분코드 :  1: 주상병(진료기간 중 치료나 검사 등에 대한 환자의 요구가 가장 컸던 상병) /  2: 부상병(진료기간 중 주상병과 함께 있었거나 발생된 상병 으로 환자 진료에 영향을 주었던 상병) /  3: 배제된 상병(최종상병이 확진된 경우 이전에 고려하였지만 배제된 상병)

			writer.write(rightPadding("J329", " ", 6)); // 상병분류기호(6)
			writer.write("01"); // 진료과목(2) : 진료를 받은 진료과목(병원급이상) 또는 상병명에 해당하는 진료 과목(의원급)을 기재하되, 진료과목이 2개 이상에 해당되는 경우 상병별로 모두 기재하며, 진료과목별 코드는 (별표5)와 같이 한다.
			writer.write(rightPadding("", " ", 2)); // 내과 세부전문과목(2) :
//			- 내과 진료과목 중 ‘세부전문의 제도인증 규정(대한의학회)’에 따라 인증받은 세부전문과목을 운영하고 있는 종합병원, 상급 종합병원의 경우 진료를 받은 세부전문과목을 기재하되, 세부 전문과목이 2개 이상인 경우 상병별로 모두 기재
//			- 세부전문과목 코드는 (별표5)를 참조
			writer.write("20230506"); // 내원일자,당월요양개시일(8) : ▪유형: CCYYMMDD
//			- 내원일자: 외래 요양급여비용 명세서의 경우 진료일자를 기재
//			- 당월요양개시일: 입원 요양급여비용 명세서의 경우 요양기관에 해당 상병 진료를 위하여 그 달에 최초 입원한 년·월·일을 기재. 단, 입원요양급여비용 분리청구시 해당 요양급여비용명세서의 최초 진료일자를 기재
			writer.write(rightPadding("1", " ", 1)); // 면허종류(1) : 1:의사 / 2:치과의사
			writer.write(rightPadding(106694+"", " ", 10)); // 면허번호(10) : 주상병명에 대하여 진료한 진료과목의 주된 의사의 면허번호 기재

			//치식구분 : 치식번호를 우상, 좌상, 우하, 좌하 순으로 일렬로 위치한 뒤 상병명과 관련된 치식번호를 (치식구분 기재 요령)과 같이 기재
			//치식구분-우상(8) : 우측 윗부분의 치아
			//치식구분-좌상(8) : 좌측 윗부분의 치아
			//치식구분-우하(8) : 우측 아랫부분의 치아
			//치식구분-좌하(8) : 좌측 아랫부분의 치아


			writer.write("\r\n");


			writer.write("2023050011"); //청구번호(10) : 2023050011
			// if no >= 100000 : "A" + leftPadding((no - 100000)+"","0",4);
			if(no2 >= 100000) {
				writer.write("A" + leftPadding((no2 - 100000)+"","0",4));
			} else {
				writer.write(leftPadding(no2+"","0",5));
			}//명세서일련번호(5) : 00001 (요양급여비용 명세서의 일련번호로 아래 예시와 같이 5자리
			//숫자로 순차적으로 기재하되 반드시 00001부터 연이어 기재
			//단, 100,000번째부터는 A0000부터 연이어 기재
			//▪유형
			//1. 50번째 명세서인 경우 다음과 같이 기재
			//- 00050
			//2. 100,500번째 명세서인 경우 다음과 같이 기재
			//- A0500

			writer.write("B"); // 내역구분(1) : B-상병내역 -- 고정값같음(하드코딩)
			writer.write("2"); // 상병분류구분(1) :
//			- 각 상병분류기호별 주․부상병, 배제된 상병을 구분하는 구분자 로서 상병분류기호별로 반드시 해당 구분자를 기재
//			- 구분코드 ‘1’(주상병)은 ‘상병분류기호’ 첫 번째 자리(제1단)의 상병에만 기재
//			▪구분코드 :  1: 주상병(진료기간 중 치료나 검사 등에 대한 환자의 요구가 가장 컸던 상병) /  2: 부상병(진료기간 중 주상병과 함께 있었거나 발생된 상병 으로 환자 진료에 영향을 주었던 상병) /  3: 배제된 상병(최종상병이 확진된 경우 이전에 고려하였지만 배제된 상병)

			writer.write(rightPadding("H6590", " ", 6)); // 상병분류기호(6)
			writer.write("12"); // 진료과목(2) : 진료를 받은 진료과목(병원급이상) 또는 상병명에 해당하는 진료 과목(의원급)을 기재하되, 진료과목이 2개 이상에 해당되는 경우 상병별로 모두 기재하며, 진료과목별 코드는 (별표5)와 같이 한다.
			writer.write(rightPadding("", " ", 2)); // 내과 세부전문과목(2) :
//			- 내과 진료과목 중 ‘세부전문의 제도인증 규정(대한의학회)’에 따라 인증받은 세부전문과목을 운영하고 있는 종합병원, 상급 종합병원의 경우 진료를 받은 세부전문과목을 기재하되, 세부 전문과목이 2개 이상인 경우 상병별로 모두 기재
//			- 세부전문과목 코드는 (별표5)를 참조
			writer.write("20230506"); // 내원일자,당월요양개시일(8) : ▪유형: CCYYMMDD
//			- 내원일자: 외래 요양급여비용 명세서의 경우 진료일자를 기재
//			- 당월요양개시일: 입원 요양급여비용 명세서의 경우 요양기관에 해당 상병 진료를 위하여 그 달에 최초 입원한 년·월·일을 기재. 단, 입원요양급여비용 분리청구시 해당 요양급여비용명세서의 최초 진료일자를 기재
			writer.write(rightPadding("", " ", 1)); // 면허종류(1) : 1:의사 / 2:치과의사
			writer.write(rightPadding("", " ", 10)); // 면허번호(10) : 주상병명에 대하여 진료한 진료과목의 주된 의사의 면허번호 기재

			//치식구분 : 치식번호를 우상, 좌상, 우하, 좌하 순으로 일렬로 위치한 뒤 상병명과 관련된 치식번호를 (치식구분 기재 요령)과 같이 기재
			//치식구분-우상(8) : 우측 윗부분의 치아
			//치식구분-좌상(8) : 좌측 윗부분의 치아
			//치식구분-우하(8) : 우측 아랫부분의 치아
			//치식구분-좌하(8) : 좌측 아랫부분의 치아

			writer.write("\r\n");
			//상병내역 for end
			//상병내역 end


			//진료내역 start
			//진료내역 for start
			writer.write("2023050011"); //청구번호(10) : 2023050011
			// if no >= 100000 : "A" + leftPadding((no - 100000)+"","0",4);
			if(no2 >= 100000) {
				writer.write("A" + leftPadding((no2 - 100000)+"","0",4));
			} else {
				writer.write(leftPadding(no2+"","0",5));
			}//명세서일련번호(5) : 00001 (요양급여비용 명세서의 일련번호로 아래 예시와 같이 5자리
			//숫자로 순차적으로 기재하되 반드시 00001부터 연이어 기재
			//단, 100,000번째부터는 A0000부터 연이어 기재
			//▪유형
			//1. 50번째 명세서인 경우 다음과 같이 기재
			//- 00050
			//2. 100,500번째 명세서인 경우 다음과 같이 기재
			//- A0500

			writer.write("C"); // 내역구분 : C : 진료내역
			writer.write("01"); // 항번호(2)
//			“진찰료”항부터 “비급여”항까지 20개 항에 부여된 번호를 기재
//			01: 진찰료 02: 입원료
//			03: 투약료 04: 주사료
//			05: 마취료 06: 이학요법료
//			07: 정신요법료 08: 처치 및 수술료
//			09: 검사료 10: 영상진단 및 방사선치료료
//			L: 요양병원·호스피스 정액
//			S: 특수장비 T: 특수재료 및 관련 행위료
//			A: 100분의50 본인부담 B: 100분의80 본인부담
//			D: 100분의30 본인부담 E: 100분의90 본인부담
//			U: 건강보험(의료급여) 100분의100본인부담
//			V: 보훈 등 100분의100본인부담
//			W: 비급여
// 				※ V항은 다음의 경우에 한하여 기재
//					- 보훈위탁진료 요양기관의 보훈국비환자 진료분
//					- 보훈병원의 국비일반(상이처, 무자격자) 또는 국비보험(급여) 2차 명세서
// 				※ W항은 다음의 경우에 한하여 기재
//					- 보훈위탁진료 요양기관의 보훈국비환자 진료분
//					- 보훈병원의 국비일반(무자격자) 또는 국비보험(급여) 2차 명세서(｢국가보훈대상자 의료지원에 관한 규칙｣ 제5조제1항 단서의 일부본인부담대상 전상군경등에 해당하는 경우)

			writer.write("01"); // 목번호(2)
//			20개 항의 소분류 단위로 부여된 번호 기재
//			▪목번호 분류 예시
//			- 진찰료
//			01: 초진 02: 재진 03: 응급 및 회송료 등
//			- 입원료
//			01: 일반 02: 내과질환자, 정신질환자, 만8세미만의 소아 03: 중환자실 04: 격리병실 10: 기본식대 11: 가산식대 12: (사용유보) 13: (사용유보) 99: 기타입원료
//			- 투약료
//			01: 내복약 02: 외용약 03: 처방전 99: 기타
//			- 주사료
//			01: 주사 99: 기타
//			- 마취료
//			01: 마취
//			- 이학요법료
//			01: 이학요법료
//			- 정신요법료
//			01: 정신요법료
//			- 처치 및 수술료
//			01: 처치 및 수술, (치과)보통처치외 처치항목 02: (치과)절개 외 수술항목 03: 캐스트 99: 치과기타
//			- 검사
//			01: 자체검사 02: 위탁검사
//			- 영상진단 및 방사선치료료
//			01: 진단 02: 치료
//			- 요양병원·호스피스 정액
//			01: 요양병원 정액수가 02: 호스피스 정액수가 81: 진찰료 82: 입원료 83: 투약료 84: 주사료
//			85: 마취료 86: 이학요법료 87: 정신요법료 88: 처치 및 수술료 89: 검사료 90: 영상진단 및 방사선치료료
//			91: 특수장비 92: 100분의 100본인부담 93: 비급여 94: 기타
//			- 특수장비
//			01: CT진단 02: MRI진단 03: PET진단 04: (사용유보) 05: (사용유보)
//			- 특수재료 및 관련 행위료
//			01: 치료재료 02: 진료행위
//			- 100분의50 본인부담
//			01: 의약품 02: 치료재료 03: 진료행위
//			- 100분의80 본인부담
//			01: 의약품 02: 치료재료 03: 진료행위
//			- 100분의30 본인부담
//			01: 의약품 02: 치료재료 03: 진료행위
//			- 100분의90 본인부담
//			01: 의약품 02: 치료재료 03: 진료행위
//			- 건강보험(의료급여) 100분의100본인부담
//			01: 의약품 02: 치료재료 03: 진료행위
//			- 보훈 등 100분의100본인부담
//			01: 의약품 02: 치료재료 03: 진료행위
//			- 비급여
//			01: 의약품 02: 치료재료 03: 진료행위

			writer.write(leftPadding(1+"", "0", 4)); // 줄번호(4) :  진료코드에 일련번호를 4자리 숫자로 부여하되 항, 목 순으로 연이어 부여 기재 /  ▪유형 (101번째 줄번호인 경우 다음과 같이 기재) - 0101
			writer.write("1"); // 코드구분(1) :
//			코드를 구분하는 구분자로서, 코드를 기재할 경우는 반드시 해당 구분자를 기재
//			▪코드구분 1: 수가(상대가치점수표에 수록된 코드) / 2: 준용수가 / 3: 보험등재약(“약제 급여 목록 및 급여 상한금액표”에 수록된 코드) / 4: 원료약, 요양기관 자체 조제(제제)약 / 8: 치료재료

			writer.write(rightPadding("AA154010", " ", 9)); // 코드(9)
			writer.write("0002162000"); // 단가(8.2) : 00021620.00
			writer.write("0000100"); // 일투(5.2) : 00001.00 // 1일투여량,투여(실시)횟수
			writer.write(leftPadding(1+"", "0", 3)); // 총투(3) : 001 // 총투여일수,실시횟수
			writer.write("000010000"); //1회투약량(5.4) : 00001.0000
			writer.write(leftPadding(21620+"","0",10)); // 금액(10) : 단가×1회투약량×1일투여량(투여(실시)횟수)×총투여일수(실시횟수)를 계산한 후 원미만은 4사5입하여 기재
			writer.write(leftPadding("", "0", 10)); // 공란(10)
			writer.write(leftPadding("", "0", 10)); // 공란(10)
			writer.write(rightPadding("", " ", 8)); // 변경일(8) : ▪유형: CCYYMMDD
//			다음의(당월요양개시일 이후에 신설되거나 단가가 변경된) 경우, 변경(또는 신설)된 단가의 최초 투여(실시)일자를 기재
//				▪당월요양개시일 이후에 단가가 변경된 경우
//					- 수가 등의 고시가가 변경 고시된 경우
//					- 실구입가 인정품목(치료재료, 조제․제제약 등)을 요양개시일 이후에 구입(조제)하여 사용한 경우
//					- 보건복지부에서 실구입가 인정품목 중 가격을 기준단가 범위 내 실구입가로 인정하도록 별도 고시한 품목의 기준단가 기준이 변경된 경우
//				▪당월요양개시일 이후에 코드가 신설된 경우
//					- 수가 항목이 신설되거나 의약품이 신규 등재된 경우, 보험등재의약품을 최초 구입 사용한 경우 등

			writer.write("1"); // 면허종류(1) : 1: 의사 2: 치과의사 6: 간호사 7: 사회복지사
			writer.write(rightPadding(106694+"", " ", 100)); //면허번호(100) :

			//치식구분 : 치식번호를 우상, 좌상, 우하, 좌하 순으로 일렬로 위치한 뒤 상병명과 관련된 치식번호를 (치식구분 기재 요령)과 같이 기재
			//치식구분-우상(8) : 우측 윗부분의 치아
			//치식구분-좌상(8) : 좌측 윗부분의 치아
			//치식구분-우하(8) : 우측 아랫부분의 치아
			//치식구분-좌하(8) : 좌측 아랫부분의 치아

			writer.write("\r\n");
			//진료내역 for end
			//진료내역 end


			//처방내역 start
			//처방내역 for start
			writer.write("2023050011"); //청구번호(10) : 2023050011
			// if no >= 100000 : "A" + leftPadding((no - 100000)+"","0",4);
			if(no2 >= 100000) {
				writer.write("A" + leftPadding((no2 - 100000)+"","0",4));
			} else {
				writer.write(leftPadding(no2+"","0",5));
			}//명세서일련번호(5) : 00001 (요양급여비용 명세서의 일련번호로 아래 예시와 같이 5자리
			//숫자로 순차적으로 기재하되 반드시 00001부터 연이어 기재
			//단, 100,000번째부터는 A0000부터 연이어 기재
			//▪유형
			//1. 50번째 명세서인 경우 다음과 같이 기재
			//- 00050
			//2. 100,500번째 명세서인 경우 다음과 같이 기재
			//- A0500
			writer.write("D"); // 내역구분(1) : D : 처방내역
			writer.write("2023050607016"); // 처방내역-처방전발급번호(13) : 요양기관에서 부여하는 번호로서 처방전 발급일과 처방전 발급 순서에 따른 일련번호를 연이어 기재
			//			▪유형: CCYYMMDD + (일련번호)
			//					CCYYMMDD: 처방전발급 년․월․일(8자리)
			//			일련번호: 처방전발급 년․월․일에 발생한 처방전의 일련 번호(5자리)
			writer.write(leftPadding(7+"", "0", 3)); // 처방일수(3)
			writer.write("00"); // 반복조제횟수(2) : 사용유보 - 00 고정값인듯
			writer.write(leftPadding(1+"", "0", 4)); // 줄번호(4) : 0001 / 처방전발급번호별로 처방약품의 일련번호를 4자리 숫자로 연이어  부여 기재 ▪유형 (3번째 줄번호인 경우 다음과 같이 기재) - 0003
			writer.write("3"); // 코드구분(1) : 처방약품의 코드를 구분하는 구분자로서, 코드를 기재할 경우는 반드시 해당 구분자를 기재 / ▪코드구분 3: 보험등재약 4: 원료약 5: 보험등재약의 일반명
			writer.write("642105020"); // 코드(9) : 처방약품의 보험등재약 또는 일반명 코드, 원료약 코드를 기재 하며 코드에 대한 세부내역은 “진료코드” 참조
			writer.write("000010000"); // 1회투약량(5.4) : 00001.0000
			writer.write(leftPadding(3+"", "0", 2)); // 1일투여횟수(2) : 03
			writer.write(leftPadding(7+"", "0", 3)); // 총투약일수(3) : 007
			writer.write(rightPadding("", " ", 1)); // 본인부담률 구분코드(1)
//			요양급여 중 「국민건강보험법 시행령」 별표 2 제4호 및 제6호에 따른 약제를 처방한 경우 본인이 부담할 비용의 부담률에 부여된 해당 구분코드를 기재
//			▪구분코드 A: 100분의50 본인부담 /  B: 100분의80 본인부담 / D: 100분의30 본인부담 / E: 100분의90 본인부담 / U: 건강보험(의료급여) 100분의100본인부담 / V: 보훈 등 100분의100본인부담 / W: 비급여

			writer.write("\r\n");

			writer.write("2023050011"); //청구번호(10) : 2023050011
			// if no >= 100000 : "A" + leftPadding((no - 100000)+"","0",4);
			if(no2 >= 100000) {
				writer.write("A" + leftPadding((no2 - 100000)+"","0",4));
			} else {
				writer.write(leftPadding(no2+"","0",5));
			}//명세서일련번호(5) : 00001 (요양급여비용 명세서의 일련번호로 아래 예시와 같이 5자리
			//숫자로 순차적으로 기재하되 반드시 00001부터 연이어 기재
			//단, 100,000번째부터는 A0000부터 연이어 기재
			//▪유형
			//1. 50번째 명세서인 경우 다음과 같이 기재
			//- 00050
			//2. 100,500번째 명세서인 경우 다음과 같이 기재
			//- A0500
			writer.write("D"); // 내역구분(1) : D : 처방내역
			writer.write("2023050607016"); // 처방내역-처방전발급번호(13) : 요양기관에서 부여하는 번호로서 처방전 발급일과 처방전 발급 순서에 따른 일련번호를 연이어 기재
			//			▪유형: CCYYMMDD + (일련번호)
			//					CCYYMMDD: 처방전발급 년․월․일(8자리)
			//			일련번호: 처방전발급 년․월․일에 발생한 처방전의 일련 번호(5자리)
			writer.write(leftPadding(7+"", "0", 3)); // 처방일수(3)
			writer.write("00"); // 반복조제횟수(2) : 사용유보 - 00 고정값인듯
			writer.write(leftPadding(2+"", "0", 4)); // 줄번호(4) : 0001 / 처방전발급번호별로 처방약품의 일련번호를 4자리 숫자로 연이어  부여 기재 ▪유형 (3번째 줄번호인 경우 다음과 같이 기재) - 0003
			writer.write("3"); // 코드구분(1) : 처방약품의 코드를 구분하는 구분자로서, 코드를 기재할 경우는 반드시 해당 구분자를 기재 / ▪코드구분 3: 보험등재약 4: 원료약 5: 보험등재약의 일반명
			writer.write("642104891"); // 코드(9) : 처방약품의 보험등재약 또는 일반명 코드, 원료약 코드를 기재 하며 코드에 대한 세부내역은 “진료코드” 참조
			writer.write("000010000"); // 1회투약량(5.4) : 00001.0000
			writer.write(leftPadding(1+"", "0", 2)); // 1일투여횟수(2) : 03
			writer.write(leftPadding(1+"", "0", 3)); // 총투약일수(3) : 007
			writer.write(rightPadding("", " ", 1)); // 본인부담률 구분코드(1)
//			요양급여 중 「국민건강보험법 시행령」 별표 2 제4호 및 제6호에 따른 약제를 처방한 경우 본인이 부담할 비용의 부담률에 부여된 해당 구분코드를 기재
//			▪구분코드 A: 100분의50 본인부담 /  B: 100분의80 본인부담 / D: 100분의30 본인부담 / E: 100분의90 본인부담 / U: 건강보험(의료급여) 100분의100본인부담 / V: 보훈 등 100분의100본인부담 / W: 비급여

			writer.write("\r\n");
			//처방내역 for end
			//처방내역 end

			//특정내역기재란 start
			//특정내역기재란 for start
			writer.write("2023050011"); //청구번호(10) : 2023050011
			// if no >= 100000 : "A" + leftPadding((no - 100000)+"","0",4);
			if(no2 >= 100000) {
				writer.write("A" + leftPadding((no2 - 100000)+"","0",4));
			} else {
				writer.write(leftPadding(no2+"","0",5));
			}//명세서일련번호(5) : 00001 (요양급여비용 명세서의 일련번호로 아래 예시와 같이 5자리
			//숫자로 순차적으로 기재하되 반드시 00001부터 연이어 기재
			//단, 100,000번째부터는 A0000부터 연이어 기재
			//▪유형
			//1. 50번째 명세서인 경우 다음과 같이 기재
			//- 00050
			//2. 100,500번째 명세서인 경우 다음과 같이 기재
			//- A0500
			writer.write("E"); // 내역구분(1) : E : 특정내역기재란
			writer.write("2"); // 방생단위구분(1) :  특정내역 발생 단위별로 해당 구분자를 기재 1: 명세서단위 / 2: 줄번호단위 / 3: 처방내역 줄번호단위 / 4: 처방내역단위
			writer.write(rightPadding("", " ", 13)); // 처방전발급번호(13) : 처방내역 줄번호단위 또는 처방내역단위로 특정내역을 기재할 경우 해당 처방전발급번호를 기재
			writer.write(leftPadding(1+"", "0", 4)); // 줄번호(4) : 진료내역 또는 처방내역의 줄번호단위로 특정내역을 기재할 경우에 해당 줄번호를 4자리 숫자로 기재(단, 명세서단위의 특정내역인 경우 에는 기재하지 않음)
			writer.write(rightPadding("JS010", " ", 5)); // 특정내역구분(5) : 해당 내역의 구분코드를 특정내역구분란에 기재하고 그에 해당 되는 기술사항을 특정내역란에 기재하되, 세부구분코드는 별표 8. “특정내역 구분코드”를 참조
			writer.write("202305061423"); // 특정내역(700) : 최대700자리까지 입력값 그대로 작성

			writer.write("\r\n");


			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testPadding() {
		try {

			System.out.println("=========================");
			System.out.println(leftPadding(1+"", "0", 6));
			System.out.println(leftPadding(352650+"", "0", 12));
			System.out.println(leftPadding(95500+"", "0", 12));
			System.out.println(leftPadding(0+"", "0", 12));
			System.out.println(leftPadding(257150+"", "0", 12));
			System.out.println(leftPadding(0+"", "0", 12));
			System.out.println(leftPadding(0+"", "0", 12));
			System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd")));
			System.out.println("=========================");

			System.out.println("==========================");
			System.out.println("테스트".getBytes("EUC-KR").length);
			System.out.println("테스트".getBytes().length);
			System.out.println(rightPadding("청구인명", "1", 20));
			System.out.println(rightPadding("문일", "1", 20));
			System.out.println(rightPadding("", "1", 5));
			System.out.println("==========================");


			System.out.println("====================================");
			// if no >= 100000 : "A" + leftPadding((no - 100000)+"","0",4);
			int no = 1;
			if(no >= 100000) {
				System.out.println("A" + leftPadding((no - 100000)+"","0",4)); //A0500
			} else {
				System.out.println(leftPadding(no+"","0",5)); //00050
			}

			System.out.println(rightPadding("1", " ", 1));
			System.out.println(rightPadding("", " ", 1));
			System.out.println(rightPadding("AA154010", " ", 9));
			System.out.println("====================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String leftPadding(String src, String ch, int num) {
		String result = "";

		if (src == null || src.length() >= num) {
			return src;
		}

		int cnt = num - src.length();

		for (int i = 0; i < cnt; i++) {
			result += ch;
		}

		return result + src;
	}

	public static String rightPadding(String src, String ch, int num) {

		String result = "";
		try {

			if (src == null || src.getBytes("EUC-KR").length >= num) {
				return src;
			}

			int cnt = num - src.getBytes("EUC-KR").length;

			for (int i = 0; i < cnt; i++) {
				result += ch;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return src + result;
	}



}