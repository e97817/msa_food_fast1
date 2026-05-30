package bo.gob.food_fast.atencion.dto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReporteResponse {
    private String contentBase64;
    private String fileName;
    private String contentType;
}
