// parameters
graph = new Object();
// type to functions
graph.funcs = new Array();
graph.funcs["pie"] = new Array("Taart","pie");
graph.funcs["donut"] = new Array("Donut","donut");
graph.funcs["text"] = new Array("Tekst","text");
graph.funcs["scatter"] = new Array("Scatter","scatter");
graph.funcs["bar"] = new Array("Staaf","bar");
// type handlers
function scatter(data){
	$("#"+graph.graphDivID).html("");
	$.jqplot(graph.graphDivID, data);
}
function text(data){
	$("#"+graph.graphDivID).html("");
	$("#"+graph.graphDivID).html(data);
}
function pie(data){
	$("#"+graph.graphDivID).html("");
	$.jqplot(graph.graphDivID, data,
		{
		  seriesDefaults: {
			// Make this a pie chart.
			renderer: jQuery.jqplot.PieRenderer,
			rendererOptions: {
			  // Put data labels on the pie slices.
			  // By default, labels show the percentage of the slice.
			  showDataLabels: true
			}
		  },
		  legend: { show:true, location: 'e' }
		}
	 );
}
function bar(data){
	$("#"+graph.graphDivID).html("");
	var values = data[0];
    // Can specify a custom tick Array.
    // Ticks should match up one for each y value (category) in the series.
    var ticks = data[1];
    var plot1 = $.jqplot(graph.graphDivID, [values], {
        // The "seriesDefaults" option is an options object that will
        // be applied to all series in the chart.
        seriesDefaults:{
            renderer:$.jqplot.BarRenderer,
            rendererOptions: {fillToZero: true,barDirection:"horizontal"}
        },
        axes: {
            // Use a category axis on the x axis and use our custom ticks.
            yaxis: {
                renderer: $.jqplot.CategoryAxisRenderer,
                ticks: ticks
            },
            // Pad the y axis just a little so bars can get close to, but
            // not touch, the grid boundaries.  1.2 is the default padding.
            xaxis: {
                pad: 1.05,
                //tickOptions: {formatString: '%d%d%d'}
            }
        }
    });
}
function table(data){}

function getData(jsonURL,catB,graphB,divD,pageID){
	graph.catButton = catB;
	graph.graphButton = graphB;
	graph.graphDivID = divD;
	$("#"+graph.graphDivID).html("Please wait, statistics are being fetched...");
	$.getJSON(jsonURL,function(json){
		//graph methods and properties
		graph.count = 0;
		graph.cats = json;
		graph.getCat = function(){
			return this.cats[this.count];
		};
		graph.nextCat = function(){
			this.count++;
			if(this.count==this.cats.length) this.count=0;
			return this.cats[this.count];
		};
		for(i=0;i<graph.cats.length;i++){
			var validCount = -1;
			//remove unknown graph types
			for(j=0;j<graph.cats[i].graphs.length;j++){
				var testType = graph.cats[i].graphs[j].type;
				if(graph.funcs[testType]==null){
					graph.cats[i].graphs.splice(j,1);
					j--;
				}else{
					if(validCount==-1)
						validCount = j;
				}
			}
			//if none are left, add text type
			if(graph.cats[i].graphs.length==0){
				graph.cats[i].graphs[0] = new Object();
				graph.cats[i].graphs[0].type = "text";
				graph.cats[i].graphs[0].data = "No valid graphs included";
				validCount = 0;
			}
			//cat methods and properties
			graph.cats[i].count = validCount;
			graph.cats[i].getGraph = function(){
				return this.graphs[this.count];
			};
			graph.cats[i].nextGraph = function(){
				this.count++;
				if(this.count==this.graphs.length) this.count = 0;
				return this.graphs[this.count];
			};
		}
		$("div#"+pageID).find("div[name='preLoadWrap']").slideUp(function(){
			$("div#"+pageID).find("div[name='loadWrap']").slideDown();
			drawGraph();
		});
	});
}
function drawGraph(){
	$(graph.catButton).text(graph.getCat().name);
	var curGraph = window.graph.getCat().getGraph();
	var type = curGraph.type;
	$(graph.graphButton).text(graph.funcs[type][0]);
	eval(graph.funcs[type][1]+"(curGraph.data)");
}

function changeCat(){
	oldCat = window.graph.getCat();
	newCat = window.graph.nextCat();
	//keep graph type if possible
	type = oldCat.getGraph().type;
	for(i=0;i<newCat.graphs.length;i++)
		if(newCat.graphs[i].type==type) newCat.count=i;
	$(graph.catButton).text(newCat.name);
	newGraph = newCat.getGraph();
	var type = newGraph.type;
	$(graph.graphButton).text(graph.funcs[type][0]);
	eval(graph.funcs[type][1]+"(newGraph.data)");
}

function changeGraph(){
	var nextGraph = window.graph.getCat().nextGraph();
	var type = nextGraph.type;
	$(graph.graphButton).text(graph.funcs[type][0]);
	eval(graph.funcs[type][1]+"(nextGraph.data)");
}