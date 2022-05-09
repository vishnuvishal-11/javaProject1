package techno;

public class GsonSample {
    private int id;
    private String name;
    private String branch;
    private String designation;
    private String[] languages_known;

    public GsonSample(int id, String name, String branch, String designation,String[] languages_known) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.designation = designation;
        this.languages_known=languages_known;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public String getDesignation() {
        return designation;
    }

    public String[] getLanguages_known() {
        return languages_known;
    }
}

