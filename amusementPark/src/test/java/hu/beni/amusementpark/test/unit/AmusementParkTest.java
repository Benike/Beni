package hu.beni.amusementpark.test.unit;

import hu.beni.amusementpark.entity.AmusementPark;
import hu.beni.amusementpark.repository.AmusementParkRepository;
import hu.beni.amusementpark.service.AmusementParkService;
import org.junit.After;
import org.junit.Before;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class AmusementParkTest {

    private AmusementParkRepository amusementParkRepository;

    private AmusementParkService amusementParkService;

    @Before
    public void setUp() {
        amusementParkRepository = mock(AmusementParkRepository.class);
        amusementParkService = new AmusementParkService(amusementParkRepository);
    }

    @After
    public void verifyNoMoreInteractionsOnMocks() {
        verifyNoMoreInteractions(amusementParkRepository);
    }

    @Test
    public void createPositive() {
        AmusementPark amusementPark = AmusementPark.builder().build();

        when(amusementParkRepository.save(amusementPark)).thenReturn(amusementPark);

        assertEquals(amusementPark, amusementParkService.create(amusementPark));

        verify(amusementParkRepository).save(amusementPark);
    }

    @Test
    public void readPositive() {
        AmusementPark amusementPark = AmusementPark.builder().id(0L).build();
        Long amusementParkId = amusementPark.getId();

        when(amusementParkRepository.findOne(amusementParkId)).thenReturn(amusementPark);

        assertEquals(amusementPark, amusementParkService.read(amusementParkId));

        verify(amusementParkRepository).findOne(amusementParkId);
    }

    @Test
    public void deletePositive() {
        Long amusementParkId = 0L;

        amusementParkService.delete(amusementParkId);

        verify(amusementParkRepository).delete(amusementParkId);
    }

}
