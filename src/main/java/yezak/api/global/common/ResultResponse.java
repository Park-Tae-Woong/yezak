package yezak.api.global.common;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultResponse<T> {

    private T data;

    private T select;

    private T page;

    private Boolean result;

    private String resultCode;

    private String resultMessage;

}

