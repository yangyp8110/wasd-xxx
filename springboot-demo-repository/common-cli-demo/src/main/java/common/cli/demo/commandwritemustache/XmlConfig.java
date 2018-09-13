package common.cli.demo.commandwritemustache;

/**
 * Created by mr.yang on 2018/5/7.
 */
public class XmlConfig {
    private String base_package;
    private String base_entity_package;
    private String base_mapper_package;

    public String getBase_package() {
        return base_package;
    }

    public void setBase_package(String base_package) {
        this.base_package = base_package;
    }

    public String getBase_entity_package() {
        return base_entity_package;
    }

    public void setBase_entity_package(String base_entity_package) {
        this.base_entity_package = base_entity_package;
    }

    public String getBase_mapper_package() {
        return base_mapper_package;
    }

    public void setBase_mapper_package(String base_mapper_package) {
        this.base_mapper_package = base_mapper_package;
    }
}
