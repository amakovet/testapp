package com.example.demo.search.Service;


import com.example.demo.search.dao.ItemRepo;
import com.example.demo.search.entity.Item;
import com.example.demo.search.pojo.ItemDTO;
import com.example.demo.search.pojo.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = mapToEntity(itemDTO);
        Item savedItem = itemRepo.save(item);
        return mapToDto(savedItem);
    }

    @Override
    public ItemResponse getAllItems(int pageNo, int pageSize, String sortBy, String sortDir) {
        //TODO: shown as pages\
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
        Page<Item> pageItems = itemRepo.findAll(pageRequest);
        List<Item> items = pageItems.getContent();
        List<ItemDTO> itemDtos = items.stream().map(this::mapToDto).collect(Collectors.toList());

        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setContent(itemDtos);
        itemResponse.setPageNo(pageItems.getNumber());
        itemResponse.setPageSize(pageItems.getSize());
        itemResponse.setTotalElements(pageItems.getTotalElements());
        itemResponse.setTotalPages(pageItems.getTotalPages());
        itemResponse.setTotalPrice(itemDtos.stream().map(ItemDTO::getPrice).count());

        return itemResponse;
    }

    @Override
    public ItemDTO getItemById(long id) {

        Item item = itemRepo.findById(id).orElseThrow(RuntimeException::new);
        return mapToDto(item);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepo.findAll();
        List<ItemDTO> itemDtos = items.stream().map(item -> mapToDto(item)).collect(Collectors.toList());
        return itemDtos;
    }





//Mapper
    private Item mapToEntity(ItemDTO itemDto){
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        return item;
    }

    private ItemDTO mapToDto(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setName(item.getName());
        return itemDTO;
    }

}
