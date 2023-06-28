package io.github.sodmomas.takeaway.users;

/**
 * @author Sod-Momas
 * @since 2023/6/29
 */
public class UsersListQuery {
   private String username;
   private Integer current;
   private Integer size;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
