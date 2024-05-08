package tn.esprit.esprithub.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Complaint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long complaintId;
    String descriptionComplaint;
    @Enumerated(EnumType.STRING)
    Cstatus statusComplaint;
    String email;

    @ManyToOne
    @JsonIgnore
    User users;

}
