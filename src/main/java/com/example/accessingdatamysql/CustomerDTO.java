package com.example.accessingdatamysql;

public class CustomerDTO {
    private Long id;
    private String name;
    private String ssn;

    public CustomerDTO(Long id, String name, String ssn) {
        this.id = id;
        this.name = name;
        this.ssn = ssn;
    }
    public CustomerDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}


