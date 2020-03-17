<!DOCTYPE html>
<html>
<head>
    <style>
        .ancestors * {
            display: block;
            border: 2px solid lightgrey;
            color: lightgrey;
            padding: 5px;
            margin: 15px;
        }
    </style>
    <script>
        $(document).ready(function(){
            $("span").closest("ul").css({"color": "red", "border": "2px solid red"});
        });
    </script>
</head>

<body class="ancestors">body (great-great-grandparent)
<div style="width:500px;">div (great-grandparent)
    <ul>ul (second ancestor - second grandparent)
        <ul>ul (first ancestor - first grandparent)
            <li>li (direct parent)
                <span>span</span>
            </li>
        </ul>
    </ul>
</div>
</body>

<!-- In this example, $("span").closest("ul") means that we search for the first ancestor of span that is an ul element. The CLOSEST ancestor of span is li, but since we are looking for a ul, jQuery skips the li element and continue the search for the next ancestor, on and on until it locates our search for ul. If we use the parents() method instead, it will return both ul ancestors. -->
</html>
