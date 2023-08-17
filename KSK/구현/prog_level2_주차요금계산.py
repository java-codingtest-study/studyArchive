import math

def calculate_parking_fee(total_minutes, basic_minute, basic_fee, minute, unit):
    if total_minutes <= basic_minute:
        return basic_fee
    else:
        extra_minutes = total_minutes - basic_minute
        extra_fee = math.ceil(extra_minutes / minute) * unit
        return basic_fee + extra_fee

def calculate_total_minutes(time1, time2):
    minutes1 = int(time1.split(':')[0]) * 60 + int(time1.split(':')[1])
    minutes2 = int(time2.split(':')[0]) * 60 + int(time2.split(':')[1])
    return minutes2 - minutes1

def solution(fees, records):
    basic_time, basic_fee, minute_unit, fee_unit = fees

    unique_cars = list(set(map(lambda x: x.split()[1], records)))
    total_fees = {car: 0 for car in unique_cars}
    check = {}

    for record in records:
        time, car_number, status = record.split(' ')
        if car_number not in check:
            check[car_number] = time
        else:
            if status == 'OUT':
                out_time = time
                in_time = check[car_number]
                total_fees[car_number] += calculate_total_minutes(in_time, out_time)
                del check[car_number]

    if check:
        for car_number, in_time in check.items():
            total_fees[car_number] += calculate_total_minutes(in_time, "23:59")

    result = []
    for car_number, total_minutes in total_fees.items():
        if total_minutes <= basic_time:
            result.append((car_number, basic_fee))
        else:
            extra_fee = calculate_parking_fee(total_minutes, basic_time, basic_fee, minute_unit, fee_unit)
            result.append((car_number, extra_fee))

    sorted_result = sorted(result)
    return [fee for car_number, fee in sorted_result]