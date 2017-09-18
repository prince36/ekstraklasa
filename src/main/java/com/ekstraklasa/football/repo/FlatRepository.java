package com.ekstraklasa.football.repo;


import com.ekstraklasa.football.model.Car;
import com.ekstraklasa.football.model.Flat;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long>, PagingAndSortingRepository<Flat, Long> {

    //@Query("SELECT c.url FROM Flat c WHERE c.url = ?1")
    List<Flat> findByUrl(String url);

    @Query("SELECT c FROM Flat c WHERE c.place = ?1 ORDER BY c.district asc, c.street asc")
    List<Flat> findByCity(String city);

    @Query("SELECT c FROM Flat c WHERE c.place = ?1 ORDER BY c.district asc, c.street asc")
    List<Flat> findByCity(String city, Pageable pageable);

    @Query("SELECT count(c) FROM Flat c where c.place=?1")
    Long countByCity(String city);

    @Query("SELECT DISTINCT c.place  FROM Flat c ORDER BY c.place asc")
    List<String> findAllCity();

    @Query("SELECT c FROM Flat c WHERE c.place = ?1 and c.district = ?2 AND c.price > ?3 and c.price < ?4 and c.num_rooms=?5 ORDER BY c.district asc, c.street asc")
    List<Flat> findForOrder(String city, String district, Integer price_from, Integer price_to, Integer numrooms);

    @Query("SELECT c FROM Flat c WHERE c.place = ?1 and c.district = ?2 AND c.price > ?3 and c.price < ?4 and c.num_rooms=?5 and c.datecreate>?6 ORDER BY c.district asc, c.street asc")
    List<Flat> findForOrderByDate(String city, String district, Integer price_from, Integer price_to, Integer numrooms, Date lastpush);

}
