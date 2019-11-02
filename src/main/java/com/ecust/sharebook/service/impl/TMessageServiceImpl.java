package com.ecust.sharebook.service.impl;

import com.ecust.sharebook.mapper.MessageInfMapper;
import com.ecust.sharebook.pojo.MessageInf;
import com.ecust.sharebook.service.TMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TMessageServiceImpl implements TMessageService {
    @Autowired
    private MessageInfMapper messageInfMapper;

    @Override
    public List<MessageInf> list(Map<String, Object> map) {
       List<MessageInf> lists =  messageInfMapper.list(map);
       if(lists.size()!=0&&lists!=null){
           return  lists;
       }
       return  null;
    }

    @Override
    public int insertSelective(MessageInf record) {
        return messageInfMapper.insertSelective(record);
    }
}