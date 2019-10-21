public class Report {
    public String projectName;
    public String campaignName;
    public Foobar foobar;

    public Report(String project_name, String campaign_name) {
        this.projectName = project_name;
        this.campaignName = campaign_name;
        this.foobar = new Foobar();
        this.foobar.x = 42;
        this.foobar.y = 3;
        this.foobar.name = "Fufuuu";
    }

    public class Foobar {
        public Integer x;
        public Integer y;
        public String name;
    }
}
