взято из теории про аннотации:

Jackson, a JSON serialization framework, uses the @JsonProperty annotation to provide
 every information necessary to modify the default serialization process:
class SimplePojo implements Serializable {
    private String name;

    @JsonProperty(value = "json_name",
                  required = true,
                  access = ACCESS.READ_ONLY)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}