package com.khovaylo.app.dto.converter;

/**
 * @author Pavel Khovaylo
 */
public interface ConverterToDto<M, D> {
    D toDto(M model);
}
