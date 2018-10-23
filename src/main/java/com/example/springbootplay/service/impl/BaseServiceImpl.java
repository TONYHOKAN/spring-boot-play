package com.example.springbootplay.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootplay.service.BaseService;


/**
 * Created by Tony Ng on 22/10/2018.
 */

/**
 * @param <M> mapper
 * @param <T> entity model
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T>
{
}
