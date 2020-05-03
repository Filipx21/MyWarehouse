package pl.exercise.warehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.exercise.warehouse.dto.ProducerDto;
import pl.exercise.warehouse.model.Producer;

@Mapper
public interface ProducerMapper {

    @Mappings({
            @Mapping(target = "id", source = "producerDto.id"),
            @Mapping(target = "companyName", source = "producerDto.companyName"),
            @Mapping(target = "owner", source = "producerDto.owner"),
            @Mapping(target = "address", source = "producerDto.address"),
            @Mapping(target = "opinion", source = "producerDto.opinion"),
            @Mapping(target = "products", source = "producerDto.products")})
    Producer toProducer(ProducerDto producerDto);

    @Mappings({
            @Mapping(target = "id", source = "producer.id"),
            @Mapping(target = "companyName", source = "producer.companyName"),
            @Mapping(target = "owner", source = "producer.owner"),
            @Mapping(target = "address", source = "producer.address"),
            @Mapping(target = "opinion", source = "producer.opinion"),
            @Mapping(target = "products", source = "producer.products")})
    ProducerDto toProducerDto(Producer producer);
}
