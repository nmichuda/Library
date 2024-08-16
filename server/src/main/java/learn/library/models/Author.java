package learn.library.models;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Author {

    private int authorId;

    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    public Author(){

    }

    public Author(int authorId, String firstName, String lastName){
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public @NotBlank(message = "first name is required") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "first name is required") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "last name is required") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "last name is required") String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, firstName, lastName);
    }
}
