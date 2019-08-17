package info.cheremisin.recipeapp.services;

import info.cheremisin.recipeapp.commands.UnitOfMeasureCommand;
import info.cheremisin.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import info.cheremisin.recipeapp.domain.UnitOfMeasure;
import info.cheremisin.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        Iterable<UnitOfMeasure> unitOfMeasures = unitOfMeasureRepository.findAll();
        Set<UnitOfMeasureCommand> commandSet = StreamSupport.stream(unitOfMeasures.spliterator(), false)
                .map(u -> converter.convert(u))
                .collect(Collectors.toSet());
        return commandSet;
    }
}
