package br.com.pegasus.api.restful.app.dto;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import br.com.pegasus.api.restful.infra.type.StatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAdviceDTO {

    private String timestamp;
    private String message;
    private int code;
    private String description;

    public ResponseAdviceDTO(StatusType status, String descriptionata) {
        this.timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(System.currentTimeMillis()));
        this.code = status.getCode();
        this.message = status.getMessage();
        this.description = descriptionata;
    }

//    @Override
//    public String toString() {
//        return MethodUtil.convertToJsonMaskValues(this);
//    }
}
