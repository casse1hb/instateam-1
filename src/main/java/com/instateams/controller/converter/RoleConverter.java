package com.instateams.controller.converter;

import com.instateams.dao.RoleDao;
import com.instateams.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RoleConverter implements Converter<String, Role>
{
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role convert(String source)
    {
        return roleDao.findById(Long.parseLong(source));
    }

    @Bean
    public ConversionService getConversionService()
    {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new RoleConverter());
        bean.setConverters(converters);
        return bean.getObject();
    }
}
