package com.pcs.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "posture_level")
public class PostureLevel extends BaseEntity {

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "neck_level")
    private Integer neckLevel;

    @Column(name = "back_front_level")
    private Integer backFrontLevel;

    public PostureLevel() {
        this.date = LocalDate.now();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getNeckLevel() {
        return neckLevel;
    }

    public void setNeckLevel(Integer neckLevel) {
        this.neckLevel = neckLevel;
    }

    public Integer getBackFrontLevel() {
        return backFrontLevel;
    }

    public void setBackFrontLevel(Integer backFrontLevel) {
        this.backFrontLevel = backFrontLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostureLevel that = (PostureLevel) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (neckLevel != null ? !neckLevel.equals(that.neckLevel) : that.neckLevel != null) return false;
        return backFrontLevel != null ? backFrontLevel.equals(that.backFrontLevel) : that.backFrontLevel == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (neckLevel != null ? neckLevel.hashCode() : 0);
        result = 31 * result + (backFrontLevel != null ? backFrontLevel.hashCode() : 0);
        return result;
    }
}
