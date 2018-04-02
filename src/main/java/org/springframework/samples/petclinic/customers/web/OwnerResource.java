
package org.springframework.samples.petclinic.customers.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.customers.model.Owner;
import org.springframework.samples.petclinic.customers.model.OwnerRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/owners")
@RestController
@RequiredArgsConstructor
@Slf4j
class OwnerResource {

	@Autowired
    private  OwnerRepository ownerRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOwner(@Valid @RequestBody Owner owner) {
        ownerRepository.save(owner);
    }
    
    @GetMapping(value = "/{ownerId}")
    public Owner findOwner(@PathVariable("ownerId") int ownerId) {
        return ownerRepository.findOne(ownerId);
    }

    /**
     * Read List of Owners
     */
    @GetMapping
    public List<Owner> findAll() {
    		System.out.println("sdasdsadasd");
        return ownerRepository.findAll();
    }

    /**
     * Update Owner
     */
    @PutMapping(value = "/{ownerId}")
    public Owner updateOwner(@PathVariable("ownerId") int ownerId, @Valid @RequestBody Owner ownerRequest) {
        final Owner ownerModel = ownerRepository.findOne(ownerId);
        // This is done by hand for simplicity purpose. In a real life use-case we should consider using MapStruct.
        ownerModel.setFirstName(ownerRequest.getFirstName());
        ownerModel.setLastName(ownerRequest.getLastName());
        ownerModel.setCity(ownerRequest.getCity());
        ownerModel.setAddress(ownerRequest.getAddress());
        ownerModel.setTelephone(ownerRequest.getTelephone());
        //log.info("Saving owner {}", ownerModel);
        return ownerRepository.save(ownerModel);
    }
}
