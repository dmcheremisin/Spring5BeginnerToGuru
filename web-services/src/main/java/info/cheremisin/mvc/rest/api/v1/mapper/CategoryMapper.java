package info.cheremisin.mvc.rest.api.v1.mapper;

import info.cheremisin.mvc.rest.api.v1.model.CategoryDTO;
import info.cheremisin.mvc.rest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
