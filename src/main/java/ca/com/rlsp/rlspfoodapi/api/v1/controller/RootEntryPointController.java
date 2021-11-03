package ca.com.rlsp.rlspfoodapi.api.v1.controller;

import ca.com.rlsp.rlspfoodapi.api.v1.links.BuildLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private BuildLinks buildLinks;

    @GetMapping
    public RootEntryPointModel root() {
        RootEntryPointModel rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(buildLinks.getLinkToCuisines("cuisines"));
        rootEntryPointModel.add(buildLinks.getLinkToCities("cities"));
        rootEntryPointModel.add(buildLinks.getLinkToPermissions("permissions"));
        rootEntryPointModel.add(buildLinks.getLinkToOrders("orders"));
        rootEntryPointModel.add(buildLinks.getLinkToRestaurants("restaurant"));
        rootEntryPointModel.add(buildLinks.getLinkToGroups("groups"));
        rootEntryPointModel.add(buildLinks.getLinkToUsers("users"));
        rootEntryPointModel.add(buildLinks.getLinkToPaymentType("payment types"));
        rootEntryPointModel.add(buildLinks.getLinkToProvinces("provinces"));
        rootEntryPointModel.add(buildLinks.getLinkToStatistics("statistics"));

        return rootEntryPointModel;
    }


    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel>{}

}
