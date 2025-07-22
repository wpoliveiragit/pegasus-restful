package br.com.pegasus.api.restful.controller.tools;

import br.com.pegasus.api.restful.util.AppConsts;
import br.com.pegasus.swagger.character.model.PaginationModel;
import br.com.pegasus.swagger.character.type.PaginationType;

import java.util.Objects;

public class ControllerTools {
    public final PaginationType toPaginationType(PaginationModel model) {
        Objects.requireNonNull(model, AppConsts.MSG_TOOLS_TO_PAGINATION_TYPE);
        return PaginationType.builder()
                .page(model.getPage())
                .size(model.getSize())
                .totalElements(model.getTotalElements())
                .totalPages(model.getTotalPages())
                .hasPrevious(model.isHasPrevious())
                .hasNext(model.isHasNext())
                .build();
    }
}
