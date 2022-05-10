package techno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class GsonSample {
    @NonNull
    private String id;
    @NonNull
    private String name;
    private String branch;
    private String designation;
    private String[] languages_known;

}

