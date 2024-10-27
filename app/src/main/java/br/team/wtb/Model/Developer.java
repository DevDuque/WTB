package br.team.wtb.Model;

public class Developer {
    private Integer photoURL;
    private String name;
    private String role;
    private String githubLink;
    private String description;

    public Developer(Integer photoURL, String name, String role, String description, String githubLink) {
        this.photoURL = photoURL;
        this.name = name;
        this.role = role;
        this.description = description;
        this.githubLink = githubLink;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", githubLink='" + githubLink + '\'' +
                ", role='" + role + '\'' +
                ", description='" + description + '\'';
    }

    public Integer getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(Integer photoURL) {
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
