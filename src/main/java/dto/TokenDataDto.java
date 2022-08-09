package dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDataDto {

    private static final long serialVersionUID = 1L;

    private String value;
    private int ttl;

}
