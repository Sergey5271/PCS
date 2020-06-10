package com.pcs.component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import com.pcs.model.SymptomType;
import com.pcs.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;


@Component
public class SymptomTypeFormatter implements Formatter<SymptomType>{

    private final SymptomRepository symptomRepository;

    public SymptomTypeFormatter(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    @Override
    public SymptomType parse(String text, Locale locale) throws ParseException {
        Collection<SymptomType> findSymptomTypes = this.symptomRepository.findSymptomTypes();
        for (SymptomType type : findSymptomTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

    @Override
    public String print(SymptomType symptomType, Locale locale) {
        return symptomType.getName();
    }
}
