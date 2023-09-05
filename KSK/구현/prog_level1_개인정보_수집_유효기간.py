def solution(today, terms, privacies):
    answer = []
    term_dict = dict()
    
    today_y, today_m, today_d=map(int, today.split("."))
    today_total = today_y * 12 * 28 + today_m * 28 + today_d
    
    for term in terms:
        term_type, term_m = term.split()
        term_dict[term_type] = int(term_m)*28 

    i=0    
    for privacy in privacies:
        p_date, p_t = privacy.split()
        p_y, p_m, p_d = map(int, p_date.split("."))
        p_total= p_y * 12 * 28 + p_m * 28 + p_d 
        
        if today_total - p_total >= term_dict[p_t]:
            answer.append(i+1)
        i+=1

    return answer