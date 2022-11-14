package com.waterleak.model.reporting;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Entity
@Table(name = "ack_nbiot")
@NoArgsConstructor
public class AckNbiot {
    @Id
    @Column(name = "IMEI", unique=true, length = 50)
    private String imei;

    @Column(name = "NB_INSTRUCTION", length = 50)
    private String nbInstruction;

    @CreatedDate
    @Column(name = "INSERT_DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar insertDate;

    @Column(name = "UPDATE_DATE", nullable = true, updatable = true)
    private Calendar updateDate;

    @Builder
    public AckNbiot(String imei, String nbInstruction, Calendar insertDate, Calendar updateDate) {
        this.imei = imei;
        this.nbInstruction = nbInstruction;
        this.insertDate = insertDate;
        this.updateDate = updateDate;
    }
}
