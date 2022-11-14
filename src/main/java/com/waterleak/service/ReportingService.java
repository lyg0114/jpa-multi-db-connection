package com.waterleak.service;

import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dto.AckNbiotDto;
import com.waterleak.model.reporting.AckNbiot;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportingService {
    private final AckNbiotRepository ackNbiotRepository;

    public AckNbiotDto getAckNbiotBy(String imei){
        Optional<AckNbiot> AckNbiotById = ackNbiotRepository.findById(imei);
        if(!AckNbiotById.isPresent())
             throw new RuntimeException("no exist imei");
        return AckNbiotById.get().convertToDto();
    }
}

