package dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String sourceDataUrl;
    private String tokenDataUrl;

}
