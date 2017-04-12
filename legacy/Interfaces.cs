using ChessSwap.ChessBoard;
using UnityEngine;

namespace ChessSwap
{
    public interface ISelectable
    {
        void Select(Tile tile);
    }
}