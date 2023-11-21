package yezak.api.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.admin.permission.account.AccountMapper;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class ScheduleService {

    private final AccountMapper accountMapper;

    //(초, 분, 시, 일, 월, 요일)
    @Scheduled(cron = "@daily") //매일 자정 하루에 한번
    public void updateEmplStatusSchedule() {
        accountMapper.updateEmplStatusSchedule();
    }

    @Scheduled(cron = "0 1 0 * * *") //매일 12시 1분
    public void updateUserStatusStopSchedule() {
        accountMapper.updateUserStatusStopSchedule();
    }
}

