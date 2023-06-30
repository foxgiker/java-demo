var year,month,day,hour;

$('#datetimepicker').datetimepicker({
  onSelectDate: function(dateText, inst){
    var d = inst.datetimepicker('getValue');
    year = d.getFullYear();
    month =d.getMonth() +1;
    day = d.getDate();
    hour =   d.getHours();
    console.log(year,month,day,hour);
  },
  onSelectTime: function(t,inst){
    var d = inst.datetimepicker('getValue');
    year = d.getFullYear();
    month =d.getMonth() +1;
    day = d.getDate();
    hour =   d.getHours();
    console.log(year,month,day,hour);
  },
  inline: true,
  format:'Y-m-d H:i',
  parentID: '#destiny-picker'
});
$.datetimepicker.setLocale('zh');

function parseData(data){
    console.log(data)
    var palaces = data.palaces;
    for(var i=0; i<palaces.length; i++){

        var cell = $('div[index^='+i+']');
        console.log(i,cell);
        cell.html("");
        var ul = $('<ul class="star-ul"> </ul>')
        for(var j=0;j<palaces[i].stars.length;j++){
            ul.append('<li class="star">'+palaces[i].stars[j].name+'</li>');
        }
        ul.appendTo(cell);

        var pcell = $('<div style="position: absolute;bottom: 0;left: 0;"><p style="margin: 0 0 0 5px;font-weight: bold;">'+ palaces[i].name +'</p></div>')
        pcell.appendTo(cell);

        var sb = $(`<div style="position: absolute;bottom: 0;right: 0;">
                                        <p style="writing-mode: vertical-rl;margin: 0 0 2px 2px;">
                                            <span class="font-bold">${palaces[i].palaceStem}</span>
                                            <span class="font-bold">${palaces[i].palaceBranch}</span>
                                        </p>
                                    </div>`)
        sb.appendTo(cell);

    }

}

$('#calcBtn').click(()=>{
    $.post(
        "/zw/calc",
        {year:year,month:month,day:day,hour:hour},
        (res)=>{
            parseData(res);
        }
    )
})


