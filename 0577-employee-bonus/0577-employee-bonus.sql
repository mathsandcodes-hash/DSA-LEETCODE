# Write your MySQL query statement below
select e.name , Bonus.bonus
from Employee as e
left join Bonus on Bonus.empID = e.empID
where bonus < 1000 or bonus is null