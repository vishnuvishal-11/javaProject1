package techno;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GsonSample { private String id;
    private String name;
    private String branch;
    private String designation;
    private String[] languages_known;

}

