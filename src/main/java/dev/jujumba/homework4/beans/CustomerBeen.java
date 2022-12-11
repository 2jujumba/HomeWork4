package dev.jujumba.homework4.beans;

import dev.jujumba.homework4.data.Customer;
import dev.jujumba.homework4.data.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBeen {
    private List<Customer> albums;

    private Employee artist;
}
