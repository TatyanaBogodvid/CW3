package com.example.cw3.services;

import com.example.cw3.exeptions.IncorrectParamException;
import com.example.cw3.model.Color;
import com.example.cw3.model.Size;
import com.example.cw3.model.Socks;
import com.example.cw3.model.SocksItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class StoreService {

    private final Map<Socks, Integer> socksMap = new HashMap<>();

    public void income(SocksItem socksItem){
        if (isNoValid(socksItem)) {
          throw new IncorrectParamException();
        }
        Socks socks = socksItem.getSocks();
        if(socksMap.containsKey(socks)){
            socksMap.replace(socks, socksMap.get(socks) + socksItem.getQuantity());
        } else{
            socksMap.put(socks, socksItem.getQuantity());
        }
    }

    public void expenditure(SocksItem socksItem){
        Socks socks = socksItem.getSocks();
        if(!socksMap.containsKey(socks) || isNoValid(socksItem)){
            throw new IncorrectParamException();
        }
        int available = socksMap.get(socks);
        int result = available - socksItem.getQuantity();
        if(result < 0){
            throw new IncorrectParamException();
        }
            socksMap.replace(socks, result);
    }

    private boolean isNoValid(SocksItem socksItem){
        Socks socks = socksItem.getSocks();
        return socks.getCottonPart() < 0 || socks.getCottonPart() > 100 || socksItem.getQuantity() <= 0;
    }

    public int count(String color, float size, int cottonMin, int cottonMax) {
        Color c = Color.parse(color);
        Size s = Size.parse(size);
        if (Objects.isNull(c) || Objects.isNull(s) || cottonMin >= cottonMax || cottonMin < 0 || cottonMax > 100) {
            throw new IncorrectParamException();
        }
        for(Map.Entry<Socks, Integer> entry : socksMap.entrySet()){
            Socks socks = entry.getKey();
            int available = entry.getValue();
            if(socks.getColor() == c && socks.getSize() == s
                    && socks.getCottonPart() >= cottonMin
                    && socks.getCottonPart() <= cottonMax){
                return available;
            }
        }
        return 0;
    }
}
