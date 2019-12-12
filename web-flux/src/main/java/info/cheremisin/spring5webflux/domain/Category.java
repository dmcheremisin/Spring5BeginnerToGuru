package info.cheremisin.spring5webflux.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Category {

    @Id
    private String id;

    private String name;
}
