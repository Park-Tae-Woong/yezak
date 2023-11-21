package yezak.api.api.admin.permission.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.admin.permission.auth.dto.*;
import yezak.api.global.common.ResultResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.StringJoiner;

import static yezak.api.config.MyIdConfig.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final AuthMapper authMapper;
    private final Integer authId = 57;
    @Autowired
    private HttpServletRequest request;

    @Override
    public ResultResponse<?> allDepthList(Long roleId) {
        if(myDepth3Id().contains(authId)) {
            List<Long> isCheckedList = authMapper.isChecked(roleId);
            List<DepthListDto> depthListDtos = authMapper.getDepthList(myHospitalId());

            for (DepthListDto dto : depthListDtos) {
                Long koId = authMapper.findByDepth1(dto.getId(), myHospitalId());
                if (koId != null) {
                    String koName = authMapper.getDepth1NameOfHospital(myHospitalId(), koId);
                    dto.setKoName(koName);
                }
                Long depth1NavigationId = dto.getId();
                List<DepthListDto2> depthListDto2 = authMapper.getDepth2List(depth1NavigationId);
                dto.setDepthListDto2(depthListDto2);

                for (DepthListDto2 dto2 : depthListDto2) {
                    Long depth2NavigationId = dto2.getId();
                    List<DepthListDto3> depthListDto3s = authMapper.getDepth3List(depth2NavigationId, roleId, myHospitalId());
                    dto2.setDepthListDto3(depthListDto3s);
                }
            }

            AllDepthList allDepthList = new AllDepthList();
            allDepthList.setDepthListDto(depthListDtos);
            allDepthList.setIsChecked(isCheckedList);

            return ResultResponse.builder()
                    .data(allDepthList)
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
    public ResultResponse<?> ShowOrHideCheck (ExposedOrNot exposedOrNot) {
        if(myDepth3Id().contains(authId)) {
            Long roleId = exposedOrNot.getRoleId();
            List<Long> depth3Ids = exposedOrNot.getDepth3NavigationIds();
            authMapper.toHideCheck(roleId);
            StringJoiner stringJoiner = new StringJoiner(", ");

            if (depth3Ids != null) {
                InsertToShow insertToShow = new InsertToShow();
                for (Long depth3Id : depth3Ids) {
                    insertToShow.setRoleId(roleId);
                    insertToShow.setDepth3NavigationId(depth3Id);
                    authMapper.toShowCheck(insertToShow);
                    stringJoiner.add(depth3Id.toString());
                }
            }
            List<Long> userIds = authMapper.findUserByRoleId(roleId);

            for(Long userId : userIds) {
                AuthLogReq authLogReq = AuthLogReq.builder()
                        .hospitalId(myHospitalId())
                        .actUserId(myUserId())
                        .depth3NavigationsArray(stringJoiner.toString())
                        .ip(getClientIp())
                        .roleId(roleId)
                        .affectedUserId(userId)
                        .build();
                authMapper.authLog(authLogReq);
            }

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
    public ResultResponse<?> getRoleCategories() {
        if(myDepth3Id().contains(authId)) {
            return ResultResponse.builder()
                    .data(authMapper.getRoleCategories())
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

    public String getClientIp() {
        if (request == null) {
            throw new IllegalArgumentException("HttpServletRequest cannot be null");
        }
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
