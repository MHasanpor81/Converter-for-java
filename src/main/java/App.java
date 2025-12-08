import dto.UserDto;
import entity.UserEntity;
import util.Converter;
import util.FieldMapper;

import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        FieldMapper<UserEntity, UserDto> entityToDtoMapper = new FieldMapper<>(UserEntity.class, UserDto.class)
                .map("name", "fullName")
                .map("email", "emailAddress");

        FieldMapper<UserDto, UserEntity> dtoToEntityMapper = new FieldMapper<>(UserDto.class, UserEntity.class)
                .map("fullName", "name")
                .map("emailAddress", "email");

//        UserEntity entity = new UserEntity();
//        entity.setEmail("mahdi@gmail.com");
//        entity.setAge(22);
//        entity.setId(1);
//        UserDto dto = Converter.convert(entity,null,entityToDtoMapper);
//        System.out.println(dto);

        UserEntity entity = new UserEntity();
        entity.setId(1);
        entity.setName("AliRezaei");
        entity.setEmail("ali@example.com");
        entity.setAge(21);
        UserDto dto = Converter.convert(entity,null,null);
        System.out.println("NewDto: "+dto);

//        UserDto newDto = new UserDto();
//        newDto.setId(2);
//        newDto.setFullName("MahdiMohammadi");
//        newDto.setEmailAddress("mahdi@example.com");
//        newDto.setAge(24);
//        UserEntity newEntity = Converter.convert(newDto,null,dtoToEntityMapper);
//        System.out.println("NewEntity: "+newEntity);
//
//        UserDto newDto2 = new UserDto();
//        Converter.convert(entity,newDto2,entityToDtoMapper);
//        System.out.println("newDto2 : " + newDto2);
//
//        Converter.convert(null,newDto,entityToDtoMapper);
    }
}
