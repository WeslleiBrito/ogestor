package com.example.ogestor.util;

import com.example.ogestor.DTO.RetornoVendaItemDTO;
import com.example.ogestor.model.RetornoVendaItem;

import java.util.List;
import java.util.stream.Collectors;

public class VendaItemMapper {

    public static RetornoVendaItemDTO toDTO(RetornoVendaItem item) {
        RetornoVendaItemDTO dto = new RetornoVendaItemDTO();
        dto.setVenda(item.getVenda());
        dto.setData_venda(item.getDataVenda().toString());
        dto.setNome_vendedor(item.getNomeVendedor());
        dto.setCod_produto(item.getCodProduto());
        dto.setDescricao(item.getDescricao());
        dto.setQtd(item.getQtd().doubleValue());
        dto.setCusto(item.getCusto().doubleValue());
        dto.setComissao(item.getComissao().doubleValue());
        dto.setDespesa_variavel(item.getDespesaVariavel().doubleValue());
        dto.setDespesa_fixa(item.getDespesaFixa().doubleValue());
        dto.setTotal(item.getTotal().doubleValue());
        dto.setLucro(item.getLucro().doubleValue());
        dto.setLucro_percentual(item.getLucroPercentual().doubleValue());
        return dto;
    }

    public static List<RetornoVendaItemDTO> toDTOList(List<RetornoVendaItem> items) {
        return items.stream().map(VendaItemMapper::toDTO).toList();
    }
}
