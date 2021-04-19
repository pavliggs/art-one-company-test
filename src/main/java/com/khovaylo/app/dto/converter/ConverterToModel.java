package com.khovaylo.app.dto.converter;

/**
 * @author Pavel Khovaylo
 */
public interface ConverterToModel<M, D> {
    M toModel(D dto);
}
