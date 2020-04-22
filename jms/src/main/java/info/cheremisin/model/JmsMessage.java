package info.cheremisin.model;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JmsMessage implements Serializable {

    public static final long serialVersionUID = 2786966509880610246L;

    private UUID id;
    private String message;
}
