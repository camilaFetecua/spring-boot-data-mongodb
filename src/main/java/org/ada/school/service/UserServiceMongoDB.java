package org.ada.school.service;

import org.ada.school.dto.UserDto;
import org.ada.school.model.User;
import org.ada.school.repository.UserDocument;
import org.ada.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceMongoDB implements UserService
{

    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository )
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDocument create(UserDocument user )
    {

        return userRepository.save(user);
    }

    @Override
    public UserDocument findById( String id )
    {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserDocument> all()
    {

        return userRepository.findAll();
    }

    @Override
    public boolean deleteById( String id )
    {

        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public UserDocument update(UserDto userDto, String id )
    {
       UserDocument usermodi =userRepository.findById(id).orElse(null);
       if (usermodi!=null){
           usermodi.update(userDto);
           return  userRepository.save(usermodi);
       }else{
           throw new NullPointerException("The user you want to modify cannot be found");

       }
    }
}

