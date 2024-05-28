package com.kindsonthegenius.fleetmsv2.assetm.services;

import com.kindsonthegenius.fleetmsv2.assetm.models.Asset;
import com.kindsonthegenius.fleetmsv2.assetm.models.DepreciatedAsset;
import com.kindsonthegenius.fleetmsv2.assetm.repositories.AssetRepository;
import com.kindsonthegenius.fleetmsv2.assetm.repositories.DepreciatedAssetRepository;
import com.kindsonthegenius.fleetmsv2.assetm.repositories.SourceRepository;
import com.kindsonthegenius.fleetmsv2.es.models.Employee;
import com.kindsonthegenius.fleetmsv2.es.repositories.EmployeeRepository;
import com.kindsonthegenius.fleetmsv2.security.models.Role;
import com.kindsonthegenius.fleetmsv2.security.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    //Method to return a list of assets
    public List<Asset> findAll() {

        return assetRepository.findAll();
    }

    //Save Asset
    public Asset save(Asset asset) {

        assetRepository.save(asset);
        return asset;
    }

    //Get Vehicle By Id
    public Asset findById(int id) {

        return assetRepository.findById(id).orElse(null);
    }

    // delect by id

    public void deleteById(int id) {

        assetRepository.deleteById(id);
    }

    public Set<Role> getUserRoles(User user) {
        return user.getRoles();
    }

    public Set<Asset> getAssets(Employee employee) {
        return employee.getAssets();
    }

//    public List<Role> getNotAssets(User user) {
//        return roleRepository.getUserNotRoles(user.getId());
//    }

    public List<Asset> getNotAssets(Employee employee) {
        return assetRepository.getNotAssets(employee.getId());
    }

//    public void assignUserRole(Integer userId, Integer roleId) {
//        User user = userRepository.findById(userId).orElse(null);
//        Role role = roleRepository.findById(roleId).orElse(null);
//        Set<Role> userRoles = user.getRoles();
//        userRoles.add(role);
//        user.setRoles(userRoles);
//        userRepository.save(user);
//    }

    // Method to assign asset
    public void assignAsset(Integer employeeId, Integer assetId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        Asset asset = assetRepository.findById(assetId).orElse(null);
        Set<Asset> employeeAssets = employee.getAssets();
        employeeAssets.add(asset);
        employee.setAssets(employeeAssets);
        employeeRepository.save(employee);
    }


//    public void unassignUserRole(Integer userId, Integer roleId) {
//        User user = userRepository.findById(userId).orElse(null);
//        user.getRoles().removeIf(x -> x.getId() == roleId);
//        userRepository.save(user);
//    }


    // Method to unassign an asset
    public void unassignAsset(Integer employeeId, Integer assetId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        employee.getAssets().removeIf(x -> x.getAsset_id() == assetId);
        employeeRepository.save(employee);
    }

//    @Autowired
//    private SourceRepository sourceRepository;
//
//    @Autowired
//    private DestinationRepository destinationRepository;
//
//    @Transactional
//    @Scheduled(cron = "0 0 0 * * *") // Execute daily at midnight
//    public void moveRecordsOlderThanFiveYears() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.YEAR, -5);
//        Date fiveYearsAgo = calendar.getTime();
//
//        List<Asset> recordsToMove = sourceRepository.findByDateInsertedBefore(fiveYearsAgo);
//        destinationRepository.save(recordsToMove);
//        sourceRepository.deleteAll(recordsToMove);
//    }

    @Autowired
    private SourceRepository sourceRepository;



//    @Scheduled(cron = "0 0 0 * * *") // Run every day at midnight
//@Scheduled(cron = "*/1 * * * * *") // Run every second
//public void migrateOldData() {
//        LocalDate fiveYearsAgo = LocalDate.now().minusYears(5);
//    Date date = Date.from(fiveYearsAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        List<Asset> oldRecords = sourceRepository.findByCreatedDateBefore(date);
//
//        System.out.println("Test  "+ oldRecords);
//        for (Asset record : oldRecords) {
//            // Create corresponding record in destination table
//            DepreciatedAsset destEntity = new DepreciatedAsset();
//            // Copy fields from sourceEntity to destEntity
//            // Assuming there are setters and getters for fields
//            destEntity.setAsset_name(record.getAsset_name());
//            // set other fields
//            destinationRepository.save(destEntity);
//
//            // Delete record from source table
//            sourceRepository.delete(record);
//
//
//        }
//
//}


    @Autowired
    private DepreciatedAssetRepository depreciatedAssetRepository;

    @Transactional
    public void migrateAssets() {
        LocalDate fiveYearsAgo = LocalDate.now().minusYears(5);
        Date date = Date.from(fiveYearsAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Asset> oldAssets = assetRepository.findOldAssets(date);
        for (Asset asset : oldAssets) {
            DepreciatedAsset depreciatedAsset = new DepreciatedAsset();
            depreciatedAsset.setAsset_name(asset.getAsset_name());
            depreciatedAsset.setCreated_at(asset.getCreated_at());
            depreciatedAsset.setAsset_description(asset.getAsset_description());
            depreciatedAsset.setAsset_model(asset.getAsset_model());
            depreciatedAsset.setAsset_serial_number(asset.getAsset_serial_number());
            // Copy other fields as needed
            depreciatedAssetRepository.save(depreciatedAsset);
        }
        assetRepository.deleteOldAssets(date);

        System.out.println("Worked");

    }

    public List<DepreciatedAsset> findDepreciatedAssest() {

        List<DepreciatedAsset>  depreciatedAssets = depreciatedAssetRepository.findAll();
        return depreciatedAssets;
    }
}
