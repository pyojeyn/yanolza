let date = new Date();

const renderCalender = () => {
  const viewYear = date.getFullYear();
  const viewMonth = date.getMonth();

  document.querySelector('.year-month').textContent = `${viewYear}년 ${viewMonth + 1}월`;

  const prevLast = new Date(viewYear, viewMonth, 0);
  const thisLast = new Date(viewYear, viewMonth + 1, 0);

  const PLDate = prevLast.getDate();
  const PLDay = prevLast.getDay();

  const TLDate = thisLast.getDate();
  const TLDay = thisLast.getDay();

  const prevDates = [];
  const thisDates = [...Array(TLDate + 1).keys()].slice(1);
  const nextDates = [];

  if (PLDay !== 6) {
    for (let i = 0; i < PLDay + 1; i++) {
      prevDates.unshift(PLDate - i);
    }
  }

  for (let i = 1; i < 7 - TLDay; i++) {
    nextDates.push(i);
  }

  const dates = prevDates.concat(thisDates, nextDates);
  const firstDateIndex = dates.indexOf(1);
  const lastDateIndex = dates.lastIndexOf(TLDate);


  //안연주 수정 0929
  //`${viewYear}-{viewMonth + 1}-${date}`;
  //
  dates.forEach((date, i) => {
    const condition = i >= firstDateIndex && i < lastDateIndex + 1
                      ? 'this'
                      : 'other';

                      dates[i] = `<div class="date" id= ${date}><span class=${condition} >${date} 
                        <form method="get" action="/hosthousemain/scheduleManagement6" style="all: unset !important;;" >
                      <a href="/hosthousemain/scheduleManagement6" ><button class="calendarbutton" type="submit" name="seldate" value="${viewYear}${viewMonth + 1}${date}">
                      <span style="color: white;">확인</span>
                    </button></a></form>  
                   
                    </span> </div>`;
  });
  //안연주 수정 0908
  //원본
  // dates.forEach((date, i) => {
  //   const condition = i >= firstDateIndex && i < lastDateIndex + 1
  //       ? 'this'
  //       : 'other';
  //
  //   dates[i] = `<div class="date" id= ${date}><span class=${condition} >${date}
  //                     <a href="/hosthousemain/scheduleManagement6" ><button class="calendarbutton">
  //                     <span style="color: white;">확인</span>
  //                   </button></a>
  //
  //                   </span> </div>`;
  // });

  document.querySelector('.dates').innerHTML = dates.join('');

  const today = new Date();
  

  if(viewMonth === today.getMonth()){
    document.getElementById(`${today.getDate()}`).classList.add('today');
    // if(document.getElementById('7').classList.add('today')){}
  
  
  }
 
  // 원래 코드
  // if (viewMonth === today.getMonth() && viewYear === today.getFullYear()) {

  //   for (let date of document.querySelectorAll('.date')) {
  //     if (+date.innerText === today.getDate()) {
  //       date.classList.add('today');
  //     break 
  //     }
  //   }

  // }
  


};


renderCalender();

const prevMonth = () => {
  date.setMonth(date.getMonth() - 1);
  renderCalender();
};

const nextMonth = () => {
  date.setMonth(date.getMonth() + 1);
  renderCalender();
};

const goToday = () => {
  date = new Date();
  renderCalender();
};